package com.example.proyecto_gestion_peliculas.data.di

import com.example.proyecto_gestion_peliculas.data.remote.api.FilmApi
import com.example.proyecto_gestion_peliculas.data.remote.api.TvSerieApi
import com.example.proyecto_gestion_peliculas.data.remote.datasource.FilmDataSource
import com.example.proyecto_gestion_peliculas.data.remote.datasource.FilmDataSourceImpl
import com.example.proyecto_gestion_peliculas.data.remote.datasource.TvSerieDataSource
import com.example.proyecto_gestion_peliculas.data.remote.datasource.TvSerieDataSourceImpl
import com.example.proyecto_gestion_peliculas.data.repository.FilmRepositoryImpl
import com.example.proyecto_gestion_peliculas.data.repository.TvSerieRepositoryImpl
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providesFilmApi(retrofit: Retrofit): FilmApi {
        return retrofit.create(FilmApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTvSerieApi(retrofit: Retrofit): TvSerieApi {
        return retrofit.create(TvSerieApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {
        @Binds
        abstract fun bindFilmRepository(impl: FilmRepositoryImpl): FilmRepository

        @Binds
        abstract fun bindTvSerieRepository(impl: TvSerieRepositoryImpl) : TvSerieRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DataSourceModule {
        @Binds
        abstract fun bindDataSourceFilmImpl(impl: FilmDataSourceImpl): FilmDataSource

        @Binds
        abstract fun bindDataSourceTvSerieImpl(impl: TvSerieDataSourceImpl) : TvSerieDataSource
    }

}