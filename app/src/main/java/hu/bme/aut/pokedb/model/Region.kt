package hu.bme.aut.pokedb.model

enum class Region(val displayName: String, val offset: Int, val limit: Int) {
    KANTO("Kanto", 0, 151),
    JOHTO("Johto", 151, 100),
    HOENN("Hoenn", 251, 135),
    SINNOH("Sinnoh", 386, 107),
    UNOVA("Unova", 493, 156),
    KALOS("Kalos", 649, 72),
    ALOLA("Alola", 721, 88),
    GALAR("Galar", 809, 89)
}