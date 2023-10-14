package com.programmer.challenge4

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.programmer.challenge4.databinding.ActivityMainBinding
import com.programmer.challenge4.fragment.HomeFragment


class FragmentDetails : AppCompatActivity(), HomeFragment.FragmentMenuListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi NavHostController
        navController = findNavController(R.id.nav_host_fragment)

        // Menampilkan FragmentMenu saat aplikasi pertama kali dibuka
        if (savedInstanceState == null) {
            navController.navigate(R.id.homeFragment)
        }
    }

    override fun onMenuItemClicked(args: Bundle) {
        // Navigasi ke FragmentDetail dengan menggunakan NavHostController
        navController.navigate(R.id.action_homeFragment_to_fragmentDetails, args)

    }

    // Override onBackPressed untuk mengatasi tombol "Back"
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val currentFragment = getCurrentFragment()

        if (currentFragment is HomeFragment) {
            // Kembali ke tampilan sebelumnya jika saat ini berada di FragmentDetails
            navController.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    // Fungsi untuk membuka Google Maps
    @SuppressLint("QueryPermissionsNeeded")
    fun openGoogleMaps(googleMapsUrl: String) {
        val gmmIntentUri = Uri.parse(googleMapsUrl)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }
}