package com.zinemaapp.data.di

import android.content.Context
import com.zinemaapp.data.remote.api.AuthApi
import com.zinemaapp.data.remote.api.FilmApi
import com.zinemaapp.data.remote.api.GenreApi
import com.zinemaapp.data.remote.api.ListApi
import com.zinemaapp.data.remote.api.TvSerieApi
import com.zinemaapp.data.remote.datasource.auth.AuthDataSource
import com.zinemaapp.data.remote.datasource.auth.AuthDataSourceImpl
import com.zinemaapp.data.remote.datasource.film.FilmDataSource
import com.zinemaapp.data.remote.datasource.film.FilmDataSourceImpl
import com.zinemaapp.data.remote.datasource.genres.GenreDataSource
import com.zinemaapp.data.remote.datasource.genres.GenreDataSourceImpl
import com.zinemaapp.data.remote.datasource.lists.ListDataSource
import com.zinemaapp.data.remote.datasource.lists.ListDataSourceImpl
import com.zinemaapp.data.remote.datasource.tvserie.TvSerieDataSource
import com.zinemaapp.data.remote.datasource.tvserie.TvSerieDataSourceImpl
import com.zinemaapp.data.remote.interceptor.AuthInterceptor
import com.zinemaapp.data.repository.AuthRepositoryImpl
import com.zinemaapp.data.repository.FilmRepositoryImpl
import com.zinemaapp.data.repository.GenreRepositoryImpl
import com.zinemaapp.data.repository.ListsRepositoryImpl
import com.zinemaapp.data.repository.TvSerieRepositoryImpl
import com.zinemaapp.domain.repository.AuthRepository
import com.zinemaapp.domain.repository.FilmRepository
import com.zinemaapp.domain.repository.GenreRepository
import com.zinemaapp.domain.repository.ListsRepository
import com.zinemaapp.domain.repository.TvSerieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(AuthInterceptor(context)).build()
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

    @Provides
    @Singleton
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesListApi(retrofit: Retrofit): ListApi {
        return retrofit.create(ListApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGenresApi(retrofit: Retrofit) : GenreApi{
        return retrofit.create(GenreApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DataSourceModule {
        @Binds
        abstract fun bindDataSourceFilmImpl(impl: FilmDataSourceImpl): FilmDataSource

        @Binds
        abstract fun bindDataSourceTvSerieImpl(impl: TvSerieDataSourceImpl): TvSerieDataSource

        @Binds
        abstract fun bindDataSourceAuthImpl(impl: AuthDataSourceImpl): AuthDataSource

        @Binds
        abstract fun bindDataSourceListImpl(impl: ListDataSourceImpl) : ListDataSource

        @Binds
        abstract fun bindDataSourceGenresImpl(impl: GenreDataSourceImpl) : GenreDataSource
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {
        @Binds
        abstract fun bindFilmRepository(impl: FilmRepositoryImpl): FilmRepository

        @Binds
        abstract fun bindTvSerieRepository(impl: TvSerieRepositoryImpl): TvSerieRepository

        @Binds
        abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

        @Binds
        abstract fun bindListRepository(impl: ListsRepositoryImpl) : ListsRepository

        @Binds
        abstract fun bindGenresRepository(impl: GenreRepositoryImpl) : GenreRepository
    }
}