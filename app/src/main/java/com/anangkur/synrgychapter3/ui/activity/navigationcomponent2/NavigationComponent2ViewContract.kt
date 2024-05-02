package com.anangkur.synrgychapter3.ui.activity.navigationcomponent2

import com.anangkur.synrgychapter3.ui.dataclass.Movie

interface NavigationComponent2ViewContract {
    fun onDataReceived(data: List<Movie>)
    fun onDataError(error: Throwable)
    fun onLoading(isLoading: Boolean)
}