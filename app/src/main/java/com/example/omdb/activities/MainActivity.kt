package com.example.omdb.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.omdb.adapters.MovieAdapter
import com.example.omdb.data.Movie
import com.example.omdb.databinding.ActivityMainBinding
import com.example.omdb.network.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter:MovieAdapter
    private var dataset: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setup RecyclerView
        movieAdapter = MovieAdapter(onItemSelected = { movieId ->
            navigateToDetail(movieId)
        })
        binding.recyclerView.adapter = movieAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.SearchButton.setOnClickListener {
            searchMovies(binding.searchEditText.text.toString())
        }
    }
    private fun searchMovies(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitProvider.apiService.searchByText(query)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    dataset = response.body()?.results!!
                    movieAdapter.updateData(dataset)
                    binding.recyclerView.visibility = View.VISIBLE
                } else {
                    println("Error: ${response.message()}")
                }
            }
        }
    }
    //Navegar desde Main a Detail
    private fun navigateToDetail(imdbID: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("IMDB_ID", imdbID)
        startActivity(intent)
    }
}