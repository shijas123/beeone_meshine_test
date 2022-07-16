package com.shijas.beeonetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shijas.beeonetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController


        navController.addOnDestinationChangedListener { _, destination, _ ->

            binding.bottomNavigationView.isVisible = destination.id == R.id.homeFragment2 ||
                    destination.id == R.id.albumFragment ||
                    destination.id == R.id.settingsFragment

        }
        binding.bottomNavigationView.setupWithNavController(navController)


    }
}