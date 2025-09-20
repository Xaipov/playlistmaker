package com.example.playlistmaker

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playlistmaker.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var trackAdapter: TrackAdapter
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trackAdapter = TrackAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = trackAdapter

        // Демо-треки добавляем внутри onCreate, а не прямо в классе
        val demoTracks = listOf(
            Track("Song 1", "Artist 1", "3:45", "https://via.placeholder.com/150"),
            Track("Song 2", "Artist 2", "2:30", "https://via.placeholder.com/150"),
            Track("Song 3", "Artist 3", "4:10", "https://via.placeholder.com/150")
        )
        trackAdapter.setTracks(demoTracks)
        setContentView(binding.root)


        savedInstanceState?.getString("search_query")?.let {
            binding.searchEditText.setText(it)
        }


        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.clearButton.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
                searchQuery = s.toString() // <-- обновляем переменную
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        binding.clearButton.setOnClickListener {
            binding.searchEditText.text.clear()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
        }


        binding.backButton.setOnClickListener { finish() }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("search_query", searchQuery) // сохраняем переменную
    }

}
