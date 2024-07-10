package com.example.movieappcompose.movie.data.mappers

import com.example.movieappcompose.movie.data.remote.response.BelongsToCollection
import com.example.movieappcompose.movie.data.remote.response.Genre
import com.example.movieappcompose.movie.data.remote.response.MovieDetail
import com.example.movieappcompose.movie.data.remote.response.ProductionCompany
import com.example.movieappcompose.movie.data.remote.response.ProductionCountry
import com.example.movieappcompose.movie.data.remote.response.SpokenLanguage
import com.example.movieappcompose.movie.domain.model.MovieDetailDomain

fun MovieDetail.toMovieDetails(): MovieDetailDomain {
    return MovieDetailDomain(
        adult = adult,
        backdrop_path = backdrop_path,
        belongs_to_collection = belongs_to_collection?.toBelongsToCollection(),
        budget = budget,
        genres = genres.map { it?.toGenre() },
        homepage = homepage,
        id = id,
        imdb_id = imdb_id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        production_companies = production_companies.map { it?.toProductionCompany() },
        production_countries = production_countries.map { it?.toProductionCountry() },
        release_date = release_date,
        revenue = revenue,
        runtime = runtime,
        spoken_languages = spoken_languages.map { it?.toSpokenLanguage() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )
}

fun BelongsToCollection.toBelongsToCollection() : com.example.movieappcompose.movie.domain.model.BelongsToCollection {
    return com.example.movieappcompose.movie.domain.model.BelongsToCollection(
        backdrop_path = backdrop_path,
        id = id,
        name = name,
        poster_path = poster_path
    )
}

fun Genre.toGenre() :com.example.movieappcompose.movie.domain.model.Genre {
    return com.example.movieappcompose.movie.domain.model.Genre (
        id = id,
        name = name
    )
}

fun ProductionCompany.toProductionCompany() : com.example.movieappcompose.movie.domain.model.ProductionCompany {
    return com.example.movieappcompose.movie.domain.model.ProductionCompany (
        id = id,
        logo_path = logo_path,
        name = name,
        origin_country = origin_country
    )
}

fun ProductionCountry.toProductionCountry() : com.example.movieappcompose.movie.domain.model.ProductionCountry {
    return com.example.movieappcompose.movie.domain.model.ProductionCountry(
        iso_3166_1 = iso_3166_1,
        name = name
    )
}

fun SpokenLanguage.toSpokenLanguage() : com.example.movieappcompose.movie.domain.model.SpokenLanguage {
    return com.example.movieappcompose.movie.domain.model.SpokenLanguage(
        english_name = english_name,
        iso_639_1 = iso_639_1,
        name = name
    )
}