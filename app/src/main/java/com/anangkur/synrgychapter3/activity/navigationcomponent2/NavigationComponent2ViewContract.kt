package com.anangkur.synrgychapter3.activity.navigationcomponent2

import com.anangkur.synrgychapter3.activity.navigationcomponent.fragment.second.data.Movie

interface NavigationComponent2ViewContract {
    fun onDataReceived(data: List<Movie>)
    fun onDataError(error: Throwable)
    fun onLoading(isLoading: Boolean)
}