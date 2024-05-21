package com.anangkur.synrgychapter3.di

import com.anangkur.synrgychapter3.ui.activity.mvvm.MvvmModel

class AppModule {
    val mvvmModel: MvvmModel by lazy { MvvmModel() }

}