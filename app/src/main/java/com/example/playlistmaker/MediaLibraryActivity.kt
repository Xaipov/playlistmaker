package com.example.playlistmaker


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.playlistmaker.databinding.ActivityMedialibraryBinding

class MediaLibraryActivity : AppCompatActivity() {

    private var _binding: ActivityMedialibraryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("settings", MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean("dark_mode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        _binding = ActivityMedialibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.backButton.setOnClickListener {
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

