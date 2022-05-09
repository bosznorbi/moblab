package hu.bme.aut.pokedb.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.model.Region
import hu.bme.aut.pokedb.repository.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.List

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    val allPokemon = MutableLiveData<Map<Int, PokemonDto>>(emptyMap())
    val displayedPokemon = MutableLiveData<List<PokemonDto>>(emptyList())

    init {
        viewModelScope.launch {
            allPokemon.value = repository.getPokemon().map { it.id to it }.toMap()
             displayedPokemon.value = getRegion(Region.KANTO)!!

            // TODO: mock, amig Moshi nem mukodik
            /*displayedPokemon.value = listOf(
                PokemonDto(id = 1, name = "Bulbasaur", type1 = Type.GRASS, type2 = Type.POISON,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
                PokemonDto(id = 2, name = "Ivysaur", type1 = Type.GRASS, type2 = Type.POISON,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"),
                PokemonDto(id = 3, name = "Venusaur", type1 = Type.GRASS, type2 = Type.POISON,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"),
                PokemonDto(id = 6, name = "Charizard", type1 = Type.FIRE, type2 = Type.FLYING,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"),
                PokemonDto(id = 112, name = "Rhydon", type1 = Type.GROUND, type2 = Type.ROCK,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/112.png"),
                PokemonDto(id = 212, name = "Scizor", type1 = Type.BUG, type2 = Type.STEEL,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/212.png")
            )*/
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