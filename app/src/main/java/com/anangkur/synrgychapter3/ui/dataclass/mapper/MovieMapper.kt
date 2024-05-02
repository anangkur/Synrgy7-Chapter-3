package com.anangkur.synrgychapter3.ui.dataclass.mapper

import com.anangkur.synrgychapter3.data.datasource.local.room.MovieEntity
import com.anangkur.synrgychapter3.ui.dataclass.Movie

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        title = title,
        description = description,
        image = image,
        id = id,
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        image = image,
        title = title,
        description = description,
        id = id,
    )
}

fun List<MovieEntity>.toMovies(): List<Movie> {
    return map { movieEntity -> movieEntity.toMovie() }
}