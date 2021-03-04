package ru.barricade2.starwarsplanetsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.barricade2.starwarsplanetsapp.database.local.PlanetsModel
import ru.barricade2.starwarsplanetsapp.database.Repository

class PlanetViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var repository: Repository = Repository(application)



    //Передача слушателю полученные данные о планете
    private val _onPlanetClicked = MutableLiveData<PlanetsModel>()
    val onPlanetClicked: LiveData<PlanetsModel>
        get() = _onPlanetClicked

    fun onPlanetClick(planetsModel: PlanetsModel) { // Click bindings
        _onPlanetClicked.value = planetsModel
    }





    // Загрузка из бд
    val planetsFromDB = repository.getPlanetsFromDB()

    // Загрузка планет из remote в local
    private val _planetsFromRemote = MutableLiveData<List<PlanetsModel>>()
    val planetsFromRemote: LiveData<List<PlanetsModel>> //
        get() = _planetsFromRemote

    // Загрузка планет из remote в бд при вызове из bindings
    fun loadPlanets() {
        _isLoading.value = true
        uiScope.launch {
            _planetsFromRemote.value =  repository.loadPlanetsFromRemote().value
        }
    }





    // Кешировать плаенеты из сети, удалив старые данные
    fun cachePlanets(planets: List<PlanetsModel>) {
        _isLoading.value = false
        uiScope.launch {
            repository.cacheSongs(planets)
        }
    }
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
