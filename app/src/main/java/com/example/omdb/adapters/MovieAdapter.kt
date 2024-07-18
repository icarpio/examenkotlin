package com.example.omdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omdb.data.Movie
import com.example.omdb.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var dataset:List<Movie> = emptyList(),
    //Navergar desde Main a Detail
    private val onItemSelected: (String) -> Unit):
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie, onItemSelected: (String) -> Unit) {
            binding.textViewTitle.text = movie.title
            binding.textViewYear.text = movie.year
            Picasso.get().load(movie.poster).into(binding.imageViewPoster)

            //Pasa el id del superheroe al Adapter
            binding.root.setOnClickListener {
                onItemSelected(movie.imdbID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //val movie = movieList[position]
        holder.bind(dataset[position], onItemSelected)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun updateData(dataset: List<Movie>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }

}
