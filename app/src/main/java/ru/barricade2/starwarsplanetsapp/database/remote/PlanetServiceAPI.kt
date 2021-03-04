package ru.barricade2.starwarsplanetsapp.database.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface PlanetServiceAPI {
    @GET("planets/")
    fun getPlanetsResponse(): Deferred<PlanetsResponse>

//    @GET("planets/{planet}")
//    fun getPlanet(@Path("planet") planet: Int): Single<PlanetsResponse>
}