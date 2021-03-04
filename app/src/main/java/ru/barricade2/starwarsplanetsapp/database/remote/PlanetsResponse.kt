package ru.barricade2.starwarsplanetsapp.database.remote

import com.google.gson.annotations.SerializedName



class PlanetsResponse {
    @SerializedName("results")
    var planets: List<Planets>? = null

    class Planets {
        @SerializedName("name")
        val namePlanet: String? = null
        @SerializedName("rotation_period")
        val rotationPeriod: String? = null
        @SerializedName("orbital_period")
        val orbitalPeriod: String? = null
        @SerializedName("population")
        val population: String? = null
    }
}
