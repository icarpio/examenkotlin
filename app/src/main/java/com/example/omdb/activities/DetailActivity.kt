package com.example.omdb.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.omdb.R
import com.example.omdb.databinding.ActivityDetailBinding
import com.example.omdb.network.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imdbID = intent.getStringExtra("IMDB_ID") ?: return
        Log.e("Detail ID", imdbID)
        getMovieDetails(imdbID)
    }

    private fun getMovieDetails(imdbID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitProvider.apiService.getMovieDetails(imdbID)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val movie = response.body()
                    movie?.let {
                        Log.e("Detail s", movie.toString())
                        //Picasso.get().load(it.poster).into(binding.imageViewPoster)
                    }
                } else {
                    println("Error: ${response.message()}")
                }
            }
        }
    }
}