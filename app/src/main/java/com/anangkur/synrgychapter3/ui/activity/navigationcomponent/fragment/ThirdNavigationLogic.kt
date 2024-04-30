package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment

import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie

class ThirdNavigationLogic {

    var title: String? = null

    fun getUrlGoogle(title: String): String {
        return "https://www.google.com/search?q=$title"
    }

    fun retrieveList(): List<Movie> {
        return emptyList()
    }
}