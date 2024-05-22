package com.anangkur.synrgychapter3

import android.app.Application
import com.anangkur.synrgychaper3.di.Module
import com.anangkur.synrgychaper3.di.koinModule
import com.anangkur.synrgychapter3.di.AppModule
import com.anangkur.synrgychapter3.di.factory.ViewModelFactory
import com.anangkur.synrgychapter3.di.factory.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    private lateinit var module: Module
    private lateinit var appModule: AppModule
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        module = Module(this)
        appModule = AppModule()
        viewModelFactory = ViewModelFactory(module)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(koinModule, viewModelModule)
        }
    }
}