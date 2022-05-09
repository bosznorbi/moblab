package hu.bme.aut.pokedb.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.pokedb.databinding.FragmentListBinding
import hu.bme.aut.pokedb.model.Region

@AndroidEntryPoint
class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val listViewModel: ListViewModel by viewModels()
    private val adapter = MyListAdapter {
        binding.root.findNavController().navigate(FragmentListDirections.actionFragmentListToFragmentDetails(it))
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
            listViewModel.chooseRegion(Region.KANTO)
        }

        binding.r2Button.setOnClickListener {
            listViewModel.chooseRegion(Region.JOHTO)
        }

        binding.r3Button.setOnClickListener {
            listViewModel.chooseRegion(Region.HOENN)
        }

        binding.r4Button.setOnClickListener {
            listViewModel.chooseRegion(Region.SINNOH)
        }

        binding.r5Button.setOnClickListener {
            listViewModel.chooseRegion(Region.UNOVA)
        }

        binding.r6Button.setOnClickListener {
            listViewModel.chooseRegion(Region.KALOS)
        }

        binding.r7Button.setOnClickListener {
            listViewModel.chooseRegion(Region.ALOLA)
        }

        binding.r8Button.setOnClickListener {
            listViewModel.chooseRegion(Region.GALAR)
        }

    }
}
