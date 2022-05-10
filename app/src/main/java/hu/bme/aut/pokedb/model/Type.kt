package hu.bme.aut.pokedb.model

enum class Type(val displayName: String, val rgb: Int) {
    NORMAL("normal", 0xA8A77A),
    FIRE("fire", 0xEE8130),
    WATER("water", 0x6390F0),
    GRASS("grass", 0x7AC74C),
    FLYING("flying", 0xA98FF3),
    FIGHTING("fighting", 0xC22E28),
    POISON("poison", 0xA33EA1),
    ELECTRIC("electric", 0xF7D02C),
    GROUND("ground", 0xE2BF65),
    ROCK("rock", 0xB6A136),
    PSYCHIC("psychic", 0xF95587),
    ICE("ice", 0x96D9D6),
    BUG("bug", 0xA6B91A),
    GHOST("ghost", 0x735797),
    STEEL("steel", 0xB7B7CE),
    DRAGON("dragon", 0x6F35FC),
    DARK("dark", 0x705746),
    FAIRY("fairy", 0xD685AD),
    NOTHING("nothing", 0x000000);

    companion object {
        private val mapping = values().associateBy(Type::displayName)
        fun fromName(symbol: String) = mapping[symbol]
    }
}