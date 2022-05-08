package hu.bme.aut.pokedb.ui.details

import hu.bme.aut.pokedb.db.PokemonDao
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.network.PokemonController
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonController: PokemonController,
    private val pokemonDao: PokemonDao
) {

    suspend fun getPokemon(): List<PokemonDto> {
        return try {
            val pokemonList = pokemonController.getPokemonList(0, 890)
            pokemonList.map { pokemonDao.insertPokemon(it.toEntity()) }
            pokemonList
        } catch (e: Exception) {
            pokemonDao.getAllPokemon().map { it.toDto() }
        }
    }
}
