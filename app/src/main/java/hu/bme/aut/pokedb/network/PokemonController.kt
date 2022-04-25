package hu.bme.aut.pokedb.network

import hu.bme.aut.pokedb.model.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonController {

    @GET("pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int?, @Query("limit") limit: Int?): List<PokemonDto>

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemon(@Path("pokemonId") pokemonId: Int): PokemonDto
}