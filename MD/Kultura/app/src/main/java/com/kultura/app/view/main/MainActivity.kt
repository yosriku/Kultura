package com.kultura.app.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kultura.app.R
import com.kultura.app.databinding.ActivityMainBinding
import com.kultura.app.view.navigation.HomeFragment
import com.kultura.app.view.navigation.ProfileFragment
import com.kultura.app.view.navigation.ScanFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_menu -> replaceFragment(HomeFragment())
                R.id.scan_menu -> replaceFragment(ScanFragment())
                R.id.profile_menu -> replaceFragment(ProfileFragment())
                else -> {
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}