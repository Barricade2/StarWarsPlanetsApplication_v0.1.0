package ru.barricade2.starwarsplanetsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.barricade2.starwarsplanetsapp.database.local.PlanetsModel
import ru.barricade2.starwarsplanetsapp.databinding.ItemPlanetsListBinding //auto xml planets_list
import ru.barricade2.starwarsplanetsapp.viewmodel.PlanetViewModel

class PlanetsListAdapter(private val planetViewModel: PlanetViewModel):
        ListAdapter<PlanetsModel, PlanetsListAdapter.ViewHolder>(
            PlanetsTableDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(planetViewModel, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemPlanetsListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(myPlanetViewModel: PlanetViewModel, myPlanetsModel: PlanetsModel) {
            with(binding) {
                planetsModel = myPlanetsModel
                planetsViewModel = myPlanetViewModel // binding planetsViewModel
                executePendingBindings()
            } }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPlanetsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding) } }
    }



    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}




class PlanetsTableDiffCallback : DiffUtil.ItemCallback<PlanetsModel>() {

    override fun areItemsTheSame(oldItem: PlanetsModel, newItem: PlanetsModel): Boolean {
        return oldItem.planetId == newItem.planetId
    }

    override fun areContentsTheSame(oldItem: PlanetsModel, newItem: PlanetsModel): Boolean {
        return oldItem == newItem
    }
}

class PlanetIconClickListener(val clickListener: (view: View, PlanetsModel: PlanetsModel) -> Unit) {
    fun onClick(view: View, PlanetsModel: PlanetsModel) = clickListener(view, PlanetsModel)
}
