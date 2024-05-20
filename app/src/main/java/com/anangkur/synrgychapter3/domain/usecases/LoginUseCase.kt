package com.anangkur.synrgychapter3.domain.usecases

import com.anangkur.synrgychapter3.domain.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository,
) {

    suspend fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            throw IllegalArgumentException("Username or password cannot be empty")
        } else {
            val token = repository.login(username, password)
            repository.saveToken(token)
        }
    }
}