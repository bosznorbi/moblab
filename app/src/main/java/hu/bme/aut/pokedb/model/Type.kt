package hu.bme.aut.pokedb.model

enum class Type(val displayName: String, val rgb: Int) {
    NORMAL("Normal", 0xA8A77A),
    FIRE("Fire", 0xEE8130),
    WATER("Water", 0x6390F0),
    GRASS("Grass", 0x7AC74C),
    FLYING("Flying", 0xA98FF3),
    FIGHTING("Fighting", 0xC22E28),
    POISON("Poison", 0xA33EA1),
    ELECTRIC("Electric", 0xF7D02C),
    GROUND("Ground", 0xE2BF65),
    ROCK("Rock", 0xB6A136),
    PSYCHIC("Psychic", 0xF95587),
    ICE("Ice", 0x96D9D6),
    BUG("Bug", 0xA6B91A),
    GHOST("Ghost", 0x735797),
    STEEL("Steel", 0xB7B7CE),
    DRAGON("Dragon", 0x6F35FC),
    DARK("Dark", 0x705746),
    FAIRY("Fairy", 0xD685AD),
    NOTHING("Nothing", 0x000000)
}