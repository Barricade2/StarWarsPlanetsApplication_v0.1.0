package ru.barricade2.starwarsplanetsapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.barricade2.starwarsplanetsapp.database.local.PlanetsDao
import ru.barricade2.starwarsplanetsapp.database.local.AppDatabase
import ru.barricade2.starwarsplanetsapp.database.local.PlanetsModel

class LocalDataSource private constructor(application: Application) {
    private val dao: PlanetsDao

    init { val db: AppDatabase = AppDatabase.getInstance(application) //db
        dao = db.planetsDao }

    //Получить из local всех планет
    fun getAllPlanets(): LiveData<List<PlanetsModel>> {
        return dao.getAllPlanets()
    }

    //Вставка планет в local
    suspend fun insertPlanets(planets: List<PlanetsModel>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(planets) }
    }


    suspend fun clearDB() {
        withContext(Dispatchers.IO) {
            dao.clear() }
    }

//    suspend fun updateSong(songTable: SongsTable) {
//        withContext(Dispatchers.IO) {
//            dao.update(songTable)
//        }
//    }

    companion object {
        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(application: Application): LocalDataSource {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = LocalDataSource(application)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}