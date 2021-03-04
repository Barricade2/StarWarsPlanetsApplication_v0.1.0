package ru.barricade2.starwarsplanetsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlanetsViewModelFactory(
        private val application: Application) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(PlanetViewModel::class.java)) {
            return PlanetViewModel(application) as T }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
