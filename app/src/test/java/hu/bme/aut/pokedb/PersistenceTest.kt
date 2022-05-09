package hu.bme.aut.pokedb

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.pokedb.db.PokemonDatabase
import hu.bme.aut.pokedb.db.PokemonEntity
import hu.bme.aut.pokedb.model.Type
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PersistenceTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: PokemonDatabase

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @After
    fun close() {
        database.close()
    }

    private val bulbasaur = PokemonEntity(id = 1, name = "Bulbasaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
    private val ivysaur = PokemonEntity(id = 2, name = "Ivysaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png")
    private val venusaur = PokemonEntity(id = 3, name = "Venusaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")
    private val charmander = PokemonEntity(id = 4, name = "Charmander", type1 = Type.FIRE, type2 = Type.NORMAL,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png")
    private val charmeleon = PokemonEntity(id = 5, name = "Charmeleon", type1 = Type.FIRE, type2 = Type.FIGHTING,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png")
    private val charizard = PokemonEntity(id = 6, name = "Charizard", type1 = Type.FIRE, type2 = Type.FLYING,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png")

    @ExperimentalCoroutinesApi
    @Test
    fun insertPokemonTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()

        // Act
        dao.insertPokemon(bulbasaur)

        // Assert
        assertEquals(1, dao.getAllPokemon().size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDataFromAllPokemonTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()

        // Act
        dao.insertPokemon(bulbasaur)

        // Assert
        assertEquals(1, dao.getAllPokemon()[0].id)
        assertEquals("Bulbasaur", dao.getAllPokemon()[0].name)
        assertEquals(Type.GRASS, dao.getAllPokemon()[0].type1)
        assertEquals(Type.POISON, dao.getAllPokemon()[0].type2)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertMultiplePokemonTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()

        // Act
        dao.insertPokemon(bulbasaur)
        dao.insertPokemon(ivysaur)
        dao.insertPokemon(venusaur)
        dao.insertPokemon(charmander)
        dao.insertPokemon(charmeleon)
        dao.insertPokemon(charizard)

        // Assert
        assertEquals(6, dao.getAllPokemon().size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun readPokemonTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()
        dao.insertPokemon(bulbasaur)
        dao.insertPokemon(ivysaur)
        dao.insertPokemon(venusaur)
        dao.insertPokemon(charmander)
        dao.insertPokemon(charmeleon)
        dao.insertPokemon(charizard)

        // Act
        val expected4 = PokemonEntity(id = 4, name = "Charmander", type1 = Type.FIRE, type2 = Type.NORMAL,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png")

        // Assert
        assertEquals(6, dao.getAllPokemon().size)
        assertEquals(1, dao.getPokemon(1).id)
        assertEquals("Ivysaur", dao.getPokemon(2).name)
        assertEquals(Type.GRASS, dao.getPokemon(3).type1)
        assertEquals(expected4, dao.getPokemon(4))
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png", dao.getPokemon(5).imageUrl)
        assertEquals(Type.FLYING, dao.getPokemon(6).type2)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertPokemonWithConflictTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()
        dao.insertPokemon(bulbasaur)

        // Act
        val conflictBulbasaurName = "Bulbasaur Conflict"
        val conflictBulbasaur = PokemonEntity(id = 1, name = conflictBulbasaurName, type1 = Type.GRASS, type2 = Type.POISON,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
        dao.insertPokemon(conflictBulbasaur)

        // Assert
        assertEquals(1, dao.getAllPokemon().size)
        assertEquals(1, dao.getPokemon(1).id)
        assertEquals(conflictBulbasaurName, dao.getPokemon(1).name)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun readPokemonDynamicTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()
        dao.insertPokemon(bulbasaur)
        dao.insertPokemon(ivysaur)
        dao.insertPokemon(venusaur)
        dao.insertPokemon(charmander)
        dao.insertPokemon(charmeleon)
        dao.insertPokemon(charizard)

        // Act
        val expected4 = PokemonEntity(id = 4, name = "Charmander", type1 = Type.FIRE, type2 = Type.NORMAL,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png")

        // Assert
        assertEquals(bulbasaur.id, dao.getPokemon(bulbasaur.id).id)
        assertEquals(ivysaur.name, dao.getPokemon(ivysaur.id).name)
        assertEquals(venusaur.type1, dao.getPokemon(venusaur.id).type1)
        assertEquals(expected4, dao.getPokemon(charmander.id))
        assertEquals(charmeleon.imageUrl, dao.getPokemon(charmeleon.id).imageUrl)
        assertEquals(charizard.type2, dao.getPokemon(charizard.id).type2)
    }

}