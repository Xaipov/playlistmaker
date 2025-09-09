package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.playlistmaker.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Кнопка "Назад"
        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Восстанавливаем текст после пересоздания Activity
        savedInstanceState?.getString("search_query")?.let { query ->
            binding.searchEditText.setText(query)
        }

        // Кнопка очистки текста (всегда активна)
        binding.clearButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    // Сохраняем текст при пересоздании Activity
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("search_query", binding.searchEditText.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
