package com.anangkur.synrgychapter3.ui.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.synrgychapter3.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class MainViewModel(private val authRepository: AuthRepository) : ViewModel() {

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