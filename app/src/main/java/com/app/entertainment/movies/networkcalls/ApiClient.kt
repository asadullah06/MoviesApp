package com.app.entertainment.movies.networkcalls

import com.app.entertainment.movies.utils.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This class will hold the retrofit client instance for the network calls.
 */
class ApiClient {

    companion object {
        private lateinit var retrofit: Retrofit
        private fun getOkHttpClient(): OkHttpClient {
            return try {
                OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .cache(null)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun getClient(): Retrofit {
            if (!this::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().setLenient().create()
                        )
                    )
                    .build()
            }
            return retrofit
        }
    }
}