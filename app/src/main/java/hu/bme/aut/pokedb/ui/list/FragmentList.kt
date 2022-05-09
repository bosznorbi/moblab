package hu.bme.aut.pokedb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import hu.bme.aut.pokedb.databinding.FragmentListBinding
import hu.bme.aut.pokedb.ui.MainActivity
import hu.bme.aut.pokedb.ui.list.ListAdapter

class FragmentList() : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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

        // val pokemonList = listViewModel.displayedPokemon.value?.toMutableList()
        // val adapter = ListAdapter(pokemonList)
        // binding.listListView.adapter = adapter
        // TODO: { } klikkre odavigyen
    }
}
