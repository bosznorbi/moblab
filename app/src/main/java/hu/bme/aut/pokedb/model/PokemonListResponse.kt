package hu.bme.aut.pokedb.model

import com.squareup.moshi.Json

val URL_LENGTH = "https://pokeapi.co/api/v2/pokemon/".length

data class PokemonListResponse(

    @Json(name = "results")
    val results: List<Results>,

    ) {
    fun toDtoList(): List<PokemonDto> {
        return results.map { it ->
            PokemonDto(
                id = it.url.dropLast(1).substring(URL_LENGTH, it.url.length).toInt(),
                name = it.name,
                type1 = Type.values().random(),
                type2 = Type.values().random(),
                imageUrl = it.url
            )
        }
    }
}

data class Results(

    @Json(name = "name")
    val name: String,

    @Json(name = "url")
    val url: String
)
