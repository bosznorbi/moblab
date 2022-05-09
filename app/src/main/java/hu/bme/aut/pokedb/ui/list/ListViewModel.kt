package hu.bme.aut.pokedb.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.model.Region
import hu.bme.aut.pokedb.repository.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    val allPokemon = MutableLiveData<Map<Int, PokemonDto>>(emptyMap())
    val displayedPokemon = MutableLiveData<List<PokemonDto>>(emptyList())

    init {
        viewModelScope.launch {
            allPokemon.value = repository.getPokemon().map { it.id to it }.toMap()
            Log.d("CUSTOM", "ListViewModel : allPokemon.value = " + allPokemon.value)
            displayedPokemon.value = getRegion(Region.KANTO)!!
            Log.d("CUSTOM", "ListViewModel : displayedPokemon.value = " + displayedPokemon.value)
        }
    }

    fun getRegion(region: Region): List<PokemonDto>? {
        return allPokemon.value
            ?.filter { (key, _) -> key >= region.offset && key <= (region.offset + region.limit) }
            ?.values
            ?.toList()
    }

    fun chooseRegion(region: Region) {
        displayedPokemon.value = getRegion(region)!!
    }
}