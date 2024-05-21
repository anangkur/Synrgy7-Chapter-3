package com.anangkur.synrgychapter3.ui.activity.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.ReqresErrorResponse
import com.anangkur.synrgychapter3.domain.repository.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

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