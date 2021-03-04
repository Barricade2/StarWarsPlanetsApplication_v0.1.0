package ru.barricade2.starwarsplanetsapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.barricade2.starwarsplanetsapp.database.local.PlanetsModel
import ru.barricade2.starwarsplanetsapp.database.remote.PlanetsResponse
import ru.barricade2.starwarsplanetsapp.database.remote.RetrofitClient

class RemoteDataSource private constructor(){
    private var retrofitClient: RetrofitClient = RetrofitClient.getInstance()

    // парсить полученные данные в таблицу
    suspend fun loadPlanets(): LiveData<List<PlanetsModel>>
    {
        val response = retrofitClient.getClient().getPlanetsResponse().await()
        return parsePlanetsResponse(response)
    }

    // parse данные из js в таблицу
    private fun parsePlanetsResponse(planetsResponse: PlanetsResponse): LiveData<List<PlanetsModel>>
    {
        val planets = planetsResponse.planets //js
        val liveData = MutableLiveData<List<PlanetsModel>>() //entity

        planets?.let { planetsList ->
            val parsedList = List(planetsList.size) { Index ->
                PlanetsModel(
                    planetName = planets[Index].namePlanet,
                    population = planets[Index].population,
                    imageUrl = "https://img.icons8.com/cotton/2x/planet--v1.png",
                    orbitalPeriod = planets[Index].orbitalPeriod,
                    rotationPeriod = planets[Index].rotationPeriod
                )
            }
            liveData.postValue(parsedList)
        }


        return liveData
    }

    companion object
    {   @Volatile
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource
        { synchronized(this)
            {
                var instance = INSTANCE
                if (instance == null)
                {
                    instance = RemoteDataSource()
                    INSTANCE = instance
                }
                return instance } } }

}