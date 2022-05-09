package hu.bme.aut.pokedb.repository

import android.util.Log
import hu.bme.aut.pokedb.db.PokemonDao
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.model.Type
import hu.bme.aut.pokedb.network.PokemonController
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonController: PokemonController,
    private val pokemonDao: PokemonDao
) {

    suspend fun getPokemon(): List<PokemonDto> {
        Log.d("CUSTOM", "PokemonRepository : started")
        try {
            val pokemonList = pokemonController.getPokemonList(0, 890)
            pokemonList.map { pokemonDao.insertPokemon(it.toEntity()) }
            if (pokemonList.isEmpty()) {
                throw Exception("Empty list")
            }
            return pokemonList
        } catch (e: Exception) {
            Log.d("CUSTOM", "PokemonRepository : failed to call API")
            val entityList = pokemonDao.getAllPokemon()
            if (entityList.isEmpty()) {
                Log.d("CUSTOM", "PokemonRepository : sent placeholder data: $PLACEHOLDER")
                return PLACEHOLDER
            } else {
                Log.d("CUSTOM", "PokemonRepository : sent offline data")
                return entityList.map { it.toDto() }
            }
        }
    }

    suspend fun getDetails(id: Int): PokemonDto {
        Log.d("CUSTOM", "PokemonRepository : started")
        return try {
            pokemonController.getPokemon(id)
        } catch (e: Exception) {
            Log.d("CUSTOM", "PokemonRepository : failed to call API")
            pokemonDao.getPokemon(id).toDto()
        }
    }

    private val PLACEHOLDER = listOf(
        PokemonDto(
            id = 1, name = "Bulbasaur", type1 = Type.GRASS, type2 = Type.POISON,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        ),
        PokemonDto(
            id = 2, name = "Ivysaur", type1 = Type.GRASS, type2 = Type.POISON,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
        ),
        PokemonDto(
            id = 3, name = "Venusaur", type1 = Type.GRASS, type2 = Type.POISON,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
        ),
        PokemonDto(
            id = 6, name = "Charizard", type1 = Type.FIRE, type2 = Type.FLYING,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"
        ),
        PokemonDto(
            id = 112, name = "Rhydon", type1 = Type.GROUND, type2 = Type.ROCK,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/112.png"
        ),
        PokemonDto(
            id = 212, name = "Scizor", type1 = Type.BUG, type2 = Type.STEEL,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/212.png"
        )
    )
}
