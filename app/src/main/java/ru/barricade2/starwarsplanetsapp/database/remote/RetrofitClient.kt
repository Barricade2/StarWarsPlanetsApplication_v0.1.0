package ru.barricade2.starwarsplanetsapp.database.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build() }


    fun getClient(): PlanetServiceAPI {
        return mRetrofit.create(PlanetServiceAPI::class.java)
    }

    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"

        @Volatile
        private var INSTANCE: RetrofitClient? = null

        fun getInstance(): RetrofitClient {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = RetrofitClient()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
