package hu.bme.aut.pokedb

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.pokedb.model.Type
import hu.bme.aut.pokedb.network.PokemonController
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import java.nio.charset.Charset
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

    @ExperimentalCoroutinesApi
    @Test
    fun getPokemonTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(getBody(context, "getPokemon133.json").orEmpty()))

        // Act
        val pokemonDto = controller.getPokemon(133).toDto()

        // Assert
        assertEquals(133, pokemonDto.id)
        assertEquals("Eevee", pokemonDto.name)
        assertEquals(Type.NORMAL, pokemonDto.type1)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPokemonListTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(getBody(context, "allPokemonFrom0To50.json").orEmpty()))

        // Act
        val pokemonList = controller.getPokemonList(0, 50).toDtoList()

        // Assert
        assertEquals(50, pokemonList.size)
        assertEquals(1, pokemonList[0].id)
        assertEquals(50, pokemonList[49].id)
    }

    private fun getBody(context: Context, path: String): String? {
        return try {
            val file = context.assets.open(path).source().buffer()
            file.readByteString().string(Charset.forName("utf-8"))
        } catch (e: IOException) { null }
    }

}