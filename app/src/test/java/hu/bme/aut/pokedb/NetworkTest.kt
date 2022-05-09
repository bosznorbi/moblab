package hu.bme.aut.pokedb

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.pokedb.db.PokemonEntity
import hu.bme.aut.pokedb.model.Type
import hu.bme.aut.pokedb.network.PokemonController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
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
class NetworkTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var controller: PokemonController

    @Inject
    lateinit var mockWebServer: MockWebServer

    @ApplicationContext
    @Inject
    lateinit var context: Context

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    private val bulbasaur = PokemonEntity(
        id = 1, name = "Bulbasaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
    )
    private val ivysaur = PokemonEntity(
        id = 2, name = "Ivysaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
    )
    private val venusaur = PokemonEntity(
        id = 3, name = "Venusaur", type1 = Type.GRASS, type2 = Type.POISON,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
    )

    @ExperimentalCoroutinesApi
    @Test
    fun readPokemonDynamicTest() = runTest {
        // Arrange

        // Act

        // Assert
    }

}