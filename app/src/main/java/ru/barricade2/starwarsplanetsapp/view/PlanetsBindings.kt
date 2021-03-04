package ru.barricade2.starwarsplanetsapp.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.barricade2.starwarsplanetsapp.GlideApp


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    GlideApp.with(context)
        .load(url)
        .into(this)
}

//@BindingAdapter("onPlanetClick")
//fun RelativeLayout.onPlanetClick(planetsModel: PlanetsModel) {
//    setOnClickListener {}
//}