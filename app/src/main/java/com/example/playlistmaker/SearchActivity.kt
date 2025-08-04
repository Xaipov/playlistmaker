 package com.example.playlistmaker

import android.renderscript.ScriptGroup.Binding
import com.example.playlistmaker.databinding.ActivitySettingsBinding


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import com.example.playlistmaker.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding!!.root)



        binding.back.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

