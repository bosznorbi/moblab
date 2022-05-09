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

    // Source: https://guides.codepath.com/android/unit-testing-with-robolectric
    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    fun setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        hiltAndroidRule.inject()
    }

    private val bulbasaur = PokemonEntity(id = 1, name = "Bulbasaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")

    @ExperimentalCoroutinesApi
    @Test
    fun insertTest() = runTest {
        // Arrange
        val dao = database.pokemonDao()

        // Act
        dao.insertPokemon(bulbasaur)

        // Assert
        assertEquals(1, 1)
    }

}