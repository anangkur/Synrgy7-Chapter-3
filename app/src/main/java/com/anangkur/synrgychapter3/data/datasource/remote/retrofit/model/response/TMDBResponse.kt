package com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response

import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.MovieResponse
import com.google.gson.annotations.SerializedName

data class TMDBResponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)
