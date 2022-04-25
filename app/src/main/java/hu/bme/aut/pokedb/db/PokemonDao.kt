package hu.bme.aut.pokedb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("select * from pokemon")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Query("select * from pokemon where id >= :offset and id <= (:offset + :limit)")
    suspend fun getAllBetweenId(offset: Int, limit: Int): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)
}