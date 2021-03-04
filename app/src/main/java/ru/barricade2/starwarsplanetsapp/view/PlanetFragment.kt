package ru.barricade2.starwarsplanetsapp.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import ru.barricade2.starwarsplanetsapp.R

import ru.barricade2.starwarsplanetsapp.database.local.PlanetsModel
import ru.barricade2.starwarsplanetsapp.databinding.FragmentPlanetBinding

class PlanetFragment: Fragment() {

    private lateinit var binding: FragmentPlanetBinding // auto

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planet, container, false)
        val planet: PlanetsModel = Gson().fromJson(requireArguments().getString(TEST), PlanetsModel::class.java)
        binding.planetsModel = planet
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val toolBar = (requireActivity() as AppCompatActivity).supportActionBar
        toolBar?.let {
            with(it) {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp) } } }

}