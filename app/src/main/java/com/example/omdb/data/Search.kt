package com.example.omdb.data

import com.google.gson.annotations.SerializedName


    data class Search(
        @SerializedName("Search") val results: List<Movie>
    )
