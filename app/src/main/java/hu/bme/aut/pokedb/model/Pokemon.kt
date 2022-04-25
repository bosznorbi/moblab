package hu.bme.aut.pokedb.model

data class Pokemon(
    val id: Int,
    val name: String,
    val type1: Type,
    val type2: Type,
    val imageUrl: String,
    val ability: String,
    val moves: Int
)