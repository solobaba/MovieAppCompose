package com.example.movieappcompose.model.network

import com.example.movieappcompose.model.network.Constants.BASE_URL
import com.example.movieappcompose.util.buildRetrofit

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val apiKey = "4710d2974e5f1a87a167e8408ab8660d"
}

val movieApiService: ApiService
    get() = buildRetrofit(BASE_URL).movieApiService