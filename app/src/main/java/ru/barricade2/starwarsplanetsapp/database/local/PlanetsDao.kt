package ru.barricade2.starwarsplanetsapp.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlanetsDao
{

    @Query("SELECT * FROM planet_table")
    fun getAllPlanets(): LiveData<List<PlanetsModel>>

    @Insert
    fun insertAll(planets: List<PlanetsModel>)

    @Query("DELETE FROM planet_table")
    fun clear()
//
//    @Update
//    fun update(planets: PlanetsModel)
//
//    @Query("SELECT * from planet_table WHERE name = :name")
//    fun getPlanetByName(name: String): PlanetsModel?
//
//    @Query("SELECT * from planet_table WHERE planetId = :planetId")
//    fun getPlanetById(planetId: Long): LiveData<PlanetsModel>
}
