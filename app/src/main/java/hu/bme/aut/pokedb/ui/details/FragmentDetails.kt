package hu.bme.aut.pokedb.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.pokedb.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class FragmentDetails : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModels()

    val fragArgs: FragmentDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        detailsViewModel.pokemon.observe(viewLifecycleOwner) {
            binding.detailsIdTextView.text = "#".plus(fragArgs.pokemonId.toString())
            binding.detailsPokemonTextView.text = detailsViewModel.pokemon.value?.name ?: ""
            binding.detailsType1TextView.text = detailsViewModel.pokemon.value?.type1?.displayName ?: ""
            binding.detailsType2TextView.text = detailsViewModel.pokemon.value?.type2?.displayName ?: ""
            binding.detailsImageUrlTextView.text = detailsViewModel.pokemon.value?.imageUrl ?: ""
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
