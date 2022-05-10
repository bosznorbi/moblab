package hu.bme.aut.pokedb.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.repository.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonId = savedStateHandle.get<Int>("pokemonId")!!

    val pokemon = MutableLiveData<PokemonDto>(null)

    init {
        viewModelScope.launch {
            pokemon.value = repository.getDetails(pokemonId)
        }
    }
}
