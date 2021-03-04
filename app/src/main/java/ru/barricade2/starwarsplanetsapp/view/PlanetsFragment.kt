package ru.barricade2.starwarsplanetsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import ru.barricade2.starwarsplanetsapp.R

import ru.barricade2.starwarsplanetsapp.databinding.FragmentPlanetsBinding // auto xml
import ru.barricade2.starwarsplanetsapp.viewmodel.PlanetViewModel
import ru.barricade2.starwarsplanetsapp.viewmodel.PlanetsViewModelFactory

class PlanetsFragment: Fragment() {

    private lateinit var binding: FragmentPlanetsBinding
    private lateinit var viewModelFactory: PlanetsViewModelFactory
    private lateinit var planetViewModel: PlanetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planets, container, false)

        val application = requireNotNull(this.activity).application
        viewModelFactory = PlanetsViewModelFactory(application)

        planetViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(PlanetViewModel::class.java)
        binding.planetsViewModel = planetViewModel      // привязка binding к planetsViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        // adapter ls, получение ViewModel
        val adapter = PlanetsListAdapter(planetViewModel)
        // привязка адаптера к RecyclerView
        binding.planetsRecyclerView.adapter = adapter   //binding RecyclerView





        planetViewModel.planetsFromDB.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.toList())
            }
        })

        // Загрузка планет из сети в бд кешировать по новому, слушатель на remote
        planetViewModel.planetsFromRemote.observe(viewLifecycleOwner, Observer {
            it?.let {
                planetViewModel.cachePlanets(it.toList())
            }
        })

        //Вывести разные сотостояния загрузки  кнопки
        planetViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.refreshButtonDefault.visibility = View.GONE
                binding.refreshButtonLoading.visibility = View.VISIBLE
            } else {
                binding.refreshButtonDefault.visibility = View.VISIBLE
                binding.refreshButtonLoading.visibility = View.GONE
            }
        })






// Click - переход к подробному описанию PlanetFragment, слушатель
        planetViewModel.onPlanetClicked.observe(viewLifecycleOwner, Observer {
            it?.let {
                val fragment = PlanetFragment() //
                val args = Bundle()
                args.putString(TEST, Gson().toJson(it))
                fragment.arguments = args

                requireActivity().supportFragmentManager.commit {
                    add(R.id.container, fragment)
                    hide(this@PlanetsFragment)
                    addToBackStack(null)
                }
            }
        })

        return binding.root
    }
}