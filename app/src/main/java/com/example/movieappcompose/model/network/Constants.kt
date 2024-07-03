package com.example.movieappcompose.model.network

import com.example.movieappcompose.model.network.Constants.BASE_URL
import com.example.movieappcompose.util.buildRetrofit

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val apiKey = "72e844a3c88498799dfdb17b65e410e1"
    const val apiToken = "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MmU4NDRhM2M4ODQ5ODc5OWRmZGIxN2I2NWU0MTBlMSIsInN1YiI6IjYzNDE2ZjlmZTFhZDc5MDA3OWVhNzg3MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QWWg8_visF75d9GacjLm_cVgTd6dzBDieSUM6C9dcho"
}

val movieApiService: ApiService
    get() = buildRetrofit(BASE_URL).movieApiService