package hu.bme.aut.pokedb.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.pokedb.databinding.FragmentListBinding
import hu.bme.aut.pokedb.model.Region

@AndroidEntryPoint
class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val listViewModel: ListViewModel by viewModels()
    private val adapter = MyListAdapter {
        binding.root.findNavController()
            .navigate(FragmentListDirections.actionFragmentListToFragmentDetails(it))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        listViewModel.displayedPokemon.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.listListView.adapter = adapter
        binding.listListView.layoutManager = LinearLayoutManager(context)

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listRandomButton.setOnClickListener {
            binding.root.findNavController().navigate(
                FragmentListDirections.actionFragmentListToFragmentDetails((0 until 890).random())
            )
        }

        binding.r1Button.setOnClickListener {
            sendFirebaseEvent(Region.KANTO)
            listViewModel.chooseRegion(Region.KANTO)
        }

        binding.r2Button.setOnClickListener {
            sendFirebaseEvent(Region.JOHTO)
            listViewModel.chooseRegion(Region.JOHTO)
        }

        binding.r3Button.setOnClickListener {
            sendFirebaseEvent(Region.HOENN)
            listViewModel.chooseRegion(Region.HOENN)
        }

        binding.r4Button.setOnClickListener {
            sendFirebaseEvent(Region.SINNOH)
            listViewModel.chooseRegion(Region.SINNOH)
        }

        binding.r5Button.setOnClickListener {
            sendFirebaseEvent(Region.UNOVA)
            listViewModel.chooseRegion(Region.UNOVA)
        }

        binding.r6Button.setOnClickListener {
            sendFirebaseEvent(Region.KALOS)
            listViewModel.chooseRegion(Region.KALOS)
        }

        binding.r7Button.setOnClickListener {
            sendFirebaseEvent(Region.ALOLA)
            listViewModel.chooseRegion(Region.ALOLA)
        }

        binding.r8Button.setOnClickListener {
            throw RuntimeException("Forced crash")
            // sendFirebaseEvent(Region.GALAR)
            // listViewModel.chooseRegion(Region.GALAR) - if forced crash button was removed
        }

    }

    fun sendFirebaseEvent(region: Region) {
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST) {
            param(FirebaseAnalytics.Param.ITEM_LIST_NAME, region.displayName)
        }
    }
}
