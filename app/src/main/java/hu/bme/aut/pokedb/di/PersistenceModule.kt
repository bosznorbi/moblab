package hu.bme.aut.pokedb.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.pokedb.db.PokemonDao
import hu.bme.aut.pokedb.db.PokemonDatabase

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java, "pokemon"
        ).build()
    }

    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }
}