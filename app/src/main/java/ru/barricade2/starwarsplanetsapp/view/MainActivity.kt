package ru.barricade2.starwarsplanetsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.barricade2.starwarsplanetsapp.R
const val TEST = "TEST"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.container, PlanetsFragment())
        }
    }


}

