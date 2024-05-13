package com.anangkur.synrgychapter3.ui.activity.register

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.anangkur.synrgychapter3.data.datasource.local.AuthLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.datastore
import com.anangkur.synrgychapter3.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.ReqresErrorResponse
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.provideReqresService
import com.anangkur.synrgychapter3.data.repository.AuthRepositoryImpl
import com.anangkur.synrgychapter3.domain.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

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
                    return RegisterViewModel(authRepository = authRepository) as T
                }
            }
    }

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _error = MutableLiveData<String>()
    val  error: LiveData<String> = _error

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _token.value = authRepository.register(name, email, password)
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                if (throwable is HttpException) {
                    val errorBody = throwable.response()?.errorBody()?.string()
                    _error.value = Gson().fromJson(errorBody, ReqresErrorResponse::class.java).error
                } else {
                    _error.value = throwable.message
                }
            }
        }
    }

}