package com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model

import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.MovieResponse
import com.anangkur.synrgychapter3.domain.model.Movie

fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        image = "https://media.themoviedb.org/t/p/w440_and_h660_face$posterPath",
        description = overview,
    )
}