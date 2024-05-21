package com.anangkur.synrgychapter3

import android.app.Application
import com.anangkur.synrgychaper3.di.Module
import com.anangkur.synrgychapter3.di.AppModule
import com.anangkur.synrgychapter3.di.factory.ViewModelFactory

class MyApplication : Application() {

    lateinit var module: Module
    lateinit var appModule: AppModule

    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        module = Module(this)
        appModule = AppModule()

        viewModelFactory = ViewModelFactory(module)
    }
}