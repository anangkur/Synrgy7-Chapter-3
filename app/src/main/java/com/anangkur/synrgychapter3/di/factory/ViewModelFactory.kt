package com.anangkur.synrgychapter3.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.synrgychaper3.di.Module
import com.anangkur.synrgychapter3.ui.activity.login.LoginViewModel
import com.anangkur.synrgychapter3.ui.activity.main.MainViewModel
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.favorite.FavoriteViewModel
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.SecondNavigationViewModel
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.third.ThirdNavigationLogic
import com.anangkur.synrgychapter3.ui.activity.navigator.NavigatorViewModel
import com.anangkur.synrgychapter3.ui.activity.register.RegisterViewModel

class ViewModelFactory(private val module: Module) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(
                loginUseCase = module.loginUseCase,
            ) as T
            MainViewModel::class.java -> MainViewModel(
                authRepository = module.authRepository,
            ) as T
            ThirdNavigationLogic::class.java -> ThirdNavigationLogic(
                movieRepository = module.movieRepository,
            ) as T
            SecondNavigationViewModel::class.java -> SecondNavigationViewModel(
                repository = module.movieRepository,
            ) as T
            FavoriteViewModel::class.java -> FavoriteViewModel(
                movieRepository = module.movieRepository,
            ) as T
            NavigatorViewModel::class.java -> NavigatorViewModel(
                authRepository = module.authRepository,
            ) as T
            RegisterViewModel::class.java -> RegisterViewModel(
                authRepository = module.authRepository,
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}