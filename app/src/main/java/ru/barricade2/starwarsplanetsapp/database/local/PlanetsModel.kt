package ru.barricade2.starwarsplanetsapp.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planet_table")
data class PlanetsModel
(
    @PrimaryKey(autoGenerate = true)
    var planetId: Long = 0L,

    @ColumnInfo(name = "name")
    var planetName: String? = "",

    @ColumnInfo(name = "population")
    var population: String? = "",

    @ColumnInfo(name = "orbital_period")
    var orbitalPeriod: String? = "",

    @ColumnInfo(name = "rotation_period")
    var rotationPeriod: String? = "",

    @ColumnInfo(name = "image_url")
    var imageUrl: String? = ""

)
