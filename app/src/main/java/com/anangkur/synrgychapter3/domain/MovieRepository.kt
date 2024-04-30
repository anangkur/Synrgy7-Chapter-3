package com.anangkur.synrgychapter3.domain

import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie

interface MovieRepository {
    fun fetchData(): List<Movie>

    fun storeData(data: Movie)
}