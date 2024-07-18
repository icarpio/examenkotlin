package com.example.omdb.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Actors") var actors: String,
    val Awards: String,
    val BoxOffice: String,
    @SerializedName("Country") var country: String,
    val DVD: String,
    @SerializedName("Director") var director: String,
    @SerializedName("Genre") var genre: String,
    val Language: String,
    val Metascore: String,
    @SerializedName("Plot") var plot: String,
    @SerializedName("Poster") var poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    @SerializedName("Runtime") var runtime: String,
    @SerializedName("Title") var title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    @SerializedName("Year") var year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String

)