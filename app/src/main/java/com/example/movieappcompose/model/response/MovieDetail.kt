package com.example.movieappcompose.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//data class MovieDetail(
//    val adult: Boolean? = false,
//    val backdrop_path: String? = null,
//    val genres: List<SortFilter>? = listOf(),
//    val id: Int? = 0,
//    val overview: String? = null,
//    val popularity: Double? = null,
//    val poster_path: String? = null,
//    val release_date: String? = null,
//    val runtime: Int? = null,
//    val title: String? = null,
//    val vote_average: Double? = null,
//    val vote_count: Int? = null
//)

@Parcelize
data class MovieDetail(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val belongs_to_collection: BelongsToCollection = BelongsToCollection(),
    val budget: Int? = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String? = "",
    val id: Int? = 0,
    val imdb_id: String? = "",
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double = 0.0,
    val poster_path: String? = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String? = "",
    val revenue: Long? = 0,
    val runtime: Long? = 0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
): Parcelable

@Parcelize
data class BelongsToCollection(
    val backdrop_path: String? = "",
    val id: Long? = 0,
    val name: String? = "",
    val poster_path: String? = ""
): Parcelable

@Parcelize
data class Genre(
    val id: Int? = 0,
    val name: String? = ""
): Parcelable

@Parcelize
data class ProductionCompany(
    val id: Int? = 0,
    val logo_path: String? = "",
    val name: String? = "",
    val origin_country: String? = ""
): Parcelable

@Parcelize
data class ProductionCountry(
    val iso_3166_1: String? = "",
    val name: String? = ""
): Parcelable

@Parcelize
data class SpokenLanguage(
    val english_name: String? = "",
    val iso_639_1: String? = "",
    val name: String? = ""
): Parcelable