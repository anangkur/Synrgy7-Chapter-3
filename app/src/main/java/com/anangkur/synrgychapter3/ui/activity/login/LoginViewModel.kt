package com.anangkur.synrgychapter3.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.ReqresErrorResponse
import com.anangkur.synrgychapter3.domain.usecase.LoginUseCase
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                loginUseCase.login(username, password)
                _loading.value = false
                _success.value = true
            } catch (throwable: Throwable) {
                _loading.value = false
                if (throwable is HttpException) {
                    val json = throwable.response()?.errorBody()?.string()
                    val error = Gson().fromJson(json, ReqresErrorResponse::class.java)
                    _error.value = error.error
                } else {
                    _error.value = throwable.message
                }
            }
        }
    }
}