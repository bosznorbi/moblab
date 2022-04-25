package hu.bme.aut.pokedb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.pokedb.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}