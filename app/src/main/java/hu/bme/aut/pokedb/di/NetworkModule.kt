package hu.bme.aut.pokedb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.pokedb.network.PokemonController
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
    }

    @Provides
    fun providePokemonController(retrofit: Retrofit): PokemonController {
        return retrofit.create(PokemonController::class.java)
    }
}