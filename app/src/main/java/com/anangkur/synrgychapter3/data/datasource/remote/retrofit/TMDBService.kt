package com.anangkur.synrgychapter3.data.datasource.remote.retrofit

import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.TMDBResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("trending/movie/{time_window}")
    @Headers(
        "accept: application/json",
        "authorization: Bearer $TMDB_TOKEN"
    )
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String = "day",
        @Query("language") language: String = "en-US",
    ): TMDBResponse

}