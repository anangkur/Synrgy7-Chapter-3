package com.anangkur.synrgychapter3.activity.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MvvmViewModel(
    private val model: MvvmModel,
) {

    private val _liveData: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<String> = _liveData

    fun incrementData() {
        model.incrementData()
         _liveData.value = model.getDataString()
    }
}