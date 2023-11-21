package com.example.movieappcompose.util

enum class SortBy(val notation: String, val displayName: String) {
    PopularityDesc("popularity.desc", "Popularity"),
    VoteCountDesc("vote_count.desc", "Vote Count"),
    ReleaseDateDesc("release_date.desc", "Release Date"),
    RevenueDesc("revenue.desc", "Revenue")
}