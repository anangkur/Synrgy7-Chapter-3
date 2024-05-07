package com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model

data class TMDBResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int,
)
