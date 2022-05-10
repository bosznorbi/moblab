package hu.bme.aut.pokedb.db;

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.model.Type

@Entity(tableName = "pokemon")
data class PokemonEntity(

    @PrimaryKey
    val id: Int,

    val name: String,
    val type1: Type,
    val type2: Type,
    val imageUrl: String
) {
    fun toDto() = PokemonDto (
        id = id,
        name = name,
        type1 = type1,
        type2 = type2,
        imageUrl = imageUrl
    )
}
