package hu.bme.aut.pokedb.network

import hu.bme.aut.pokedb.model.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonController {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int?, @Query("limit") limit: Int?): List<PokemonDto> // TODO: List<PokemonResponse>

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemon(@Path("pokemonId") pokemonId: Int): PokemonDto // TODO: PokemonResponse
}