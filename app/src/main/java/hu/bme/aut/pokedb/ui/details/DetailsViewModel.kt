package hu.bme.aut.pokedb.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.model.Type
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    // private val repository: PokemonRepository
) : ViewModel() {

    val pokemon = MutableLiveData<PokemonDto>(null)

    init {
        viewModelScope.launch {
            pokemon.value = PokemonDto(
                id = 1, name = "Bulbasaur", type1 = Type.GRASS, type2 = Type.POISON,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            )
            // ----- VALODI MUKODES:
            // pokemon.value = repository.getDetails(1)
        }
    }
}