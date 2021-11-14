package com.app.entertainment.movies.di

import com.app.entertainment.movies.networkcalls.ApiHelper
import com.app.entertainment.movies.repository.DefaultMoviesRepository
import com.app.entertainment.movies.repository.MoviesRepository
import com.app.entertainment.movies.utils.BASE_URL
import com.app.entertainment.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiHelper(): ApiHelper = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiHelper::class.java)

    @Singleton
    @Provides
    fun provideMoviesRepository(api: ApiHelper): MoviesRepository =
        DefaultMoviesRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }


}