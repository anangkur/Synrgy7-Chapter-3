package com.anangkur.synrgychapter3.di.factory

import com.anangkur.synrgychapter3.ui.activity.login.LoginViewModel
import com.anangkur.synrgychapter3.ui.activity.main.MainViewModel
import com.anangkur.synrgychapter3.ui.activity.mvvm.MvvmModel
import com.anangkur.synrgychapter3.ui.activity.mvvm.MvvmViewModel
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.favorite.FavoriteViewModel
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.SecondNavigationViewModel
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.third.ThirdNavigationLogic
import com.anangkur.synrgychapter3.ui.activity.navigator.NavigatorViewModel
import com.anangkur.synrgychapter3.ui.activity.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { MainViewModel(authRepository = get()) }
    viewModel { ThirdNavigationLogic(movieRepository = get()) }
    viewModel { SecondNavigationViewModel(repository = get()) }
    viewModel { FavoriteViewModel(movieRepository = get()) }
    viewModel { NavigatorViewModel(authRepository = get()) }
    viewModel { RegisterViewModel(authRepository = get()) }
    single<MvvmModel> { MvvmModel() }
    single<MvvmViewModel> { MvvmViewModel(get()) }
}