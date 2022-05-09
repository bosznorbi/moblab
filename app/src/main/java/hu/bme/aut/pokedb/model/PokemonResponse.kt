package hu.bme.aut.pokedb.model

import com.squareup.moshi.Json

data class PokemonResponse(

    @Json(name = "id")
    val id: Int,

    @Json(name = "id")
    val name: String,

    @Json(name = "types")
    val types: List<Types>,

    @Json(name = "sprites")
    val sprites: Sprites

) {
    fun toDto() = PokemonDto(
        id = id,
        name = name,
        type1 = Type.fromName(types[0].type.name)!!,
        type2 = Type.values().random(), // TODO: Type.fromName(types[1].type.name),
        imageUrl = sprites.frontDefault
    )
}

data class Sprites(

    @Json(name = "front_default")
    val frontDefault: String
)

data class Types(

    @Json(name = "type")
    val type: Type2
)

data class Type2(

    @Json(name = "name")
    val name: String
)
