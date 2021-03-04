package ru.barricade2.starwarsplanetsapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.barricade2.starwarsplanetsapp.database.local.PlanetsModel

class Repository(application: Application) {
    private val remoteDataSource = RemoteDataSource.getInstance() //Remote
    private val dbDataSource = LocalDataSource.getInstance(application) //Local
    // получить данные из local
    private val planetsFromDB = dbDataSource.getAllPlanets()

    // загрузка планет из сети-remote в local
    suspend fun loadPlanetsFromRemote(): LiveData<List<PlanetsModel>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.loadPlanets() //парсить данные в таблицу
        }
    }

    // преобразовать данные из local в  поток LiveData
    fun getPlanetsFromDB(): LiveData<List<PlanetsModel>> = planetsFromDB

    // кешировать заново планеты из remote в local
    suspend fun cacheSongs(planets: List<PlanetsModel>?) {
        planets?.let {
            withContext(Dispatchers.IO) {
                dbDataSource.clearDB()
                dbDataSource.insertPlanets(planets)
            }
        }
    }
}