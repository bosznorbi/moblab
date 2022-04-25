package hu.bme.aut.pokedb.model

enum class Region(val displayName: String, val first: Int, val last: Int) {
    KANTO("Kanto", 1, 151),
    JOHTO("Johto", 152, 251),
    HOENN("Hoenn", 252, 386),
    SINNOH("Sinnoh", 387, 493),
    UNOVA("Unova", 494, 649),
    KALOS("Kalos", 650, 721),
    ALOLA("Alola", 722, 809),
    GALAR("Galar", 810, 898)
}