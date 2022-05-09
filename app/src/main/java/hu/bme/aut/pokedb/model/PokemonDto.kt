package hu.bme.aut.pokedb.model

import hu.bme.aut.pokedb.db.PokemonEntity

data class PokemonDto(
    val id: Int,
    val name: String,
    val type1: Type,
    val type2: Type,
    val imageUrl: String
) {
    fun toEntity() = PokemonEntity(
        id = id,
        name = name,
        type1 = type1,
        type2 = type2,
        imageUrl = imageUrl
    )
}
