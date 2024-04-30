package com.anangkur.synrgychapter3.ui.activity.mvvm

class MvvmModel {
    private var data: Int = 0

    fun incrementData(): Int {
        return data++
    }

    fun getDataString(): String {
        return data.toString()
    }
}