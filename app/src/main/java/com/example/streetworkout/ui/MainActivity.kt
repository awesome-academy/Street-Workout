package com.example.streetworkout.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.streetworkout.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_TransparentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.fragmentNavHost)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigationHome,
                R.id.navigationNutrition,
                R.id.navigationSettings
            )
        )

        bottomNavView.setupWithNavController(navController)
    }

    override fun showBottomNav() {
        bottomNavView.visibility = View.VISIBLE
    }
}
