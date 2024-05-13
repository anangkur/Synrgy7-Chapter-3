package com.anangkur.synrgychapter3.ui.activity.main

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.anangkur.synrgychapter3.data.datasource.local.AuthLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.datastore
import com.anangkur.synrgychapter3.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.provideReqresService
import com.anangkur.synrgychapter3.data.repository.AuthRepositoryImpl
import com.anangkur.synrgychapter3.domain.AuthRepository
import kotlinx.coroutines.launch

class MainViewModel(private val authRepository: AuthRepository) : ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    val authRepository: AuthRepository = AuthRepositoryImpl(
                        authLocalDataSource = AuthLocalDataSourceImpl(
                            dataStore = context.datastore,
                        ),
                        authRemoteDataSource = AuthRemoteDataSourceImpl(
                            reqresService = provideReqresService(context),
                        ),
                    )
                    return MainViewModel(authRepository = authRepository) as T
                }
            }
    }

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> = _error

    fun logout() {
        viewModelScope.launch {
            try {
                authRepository.clearToken()
            } catch (throwable: Throwable) {
                _error.value = throwable.message
            }
        }
    }
}