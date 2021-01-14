package com.github.avanlex.fundamentalsassignments


object ApiConfig {
    val apiKey = "0caeddbe95dc9881e4c2e4c731e573bc"
    val apiKeyV4Auth = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwY2FlZGRiZTk1ZGM5ODgxZTRjMmU0YzczMWU1NzNiYyIsInN1YiI6IjVmZTc2ZTNiNDg5ZDAwMDAzZDk5ODI1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R0Zn8MVVQvy0v-64Rki86aGLkUXYZl-WTXvOtxPjawY"
    val jsonMedia = "application/json"
    val language = "en-US" // ru-RU
    val profileImageSize = "w500"
}
enum class MovieSortType(val string: String){
    PopularityAsc("popularity.asc"),
    PopularityDesc("popularity.desc"),
    ReleaseDateAsc("release_date.asc"),
    ReleaseDateDesc("release_date.desc"),
    RevenueAsc("revenue.asc"),
    RevenueDesc("revenue.desc"),
    PrimaryReleaseDateAsc("primary_release_date.asc"),
    PrimaryReleaseDateDesc("primary_release_date.desc"),
    OriginalTitleAsc("original_title.asc"),
    OriginalTitleDesc("original_title.desc"),
    VoteAverageAsc("vote_average.asc"),
    VoteAverageDesc("vote_average.desc"),
    VoteCountAsc("vote_count.asc"),
    VoteCountDesc("vote_count.desc"),
}

