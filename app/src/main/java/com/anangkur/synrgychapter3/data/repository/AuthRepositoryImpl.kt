package com.anangkur.synrgychapter3.data.repository

import com.anangkur.synrgychapter3.data.datasource.AuthLocalDataSource
import com.anangkur.synrgychapter3.data.datasource.AuthRemoteDataSource
import com.anangkur.synrgychapter3.domain.AuthRepository

class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun login(username: String, password: String): String {
        return authRemoteDataSource.login(username, password)
    }

    override suspend fun register(username: String, email: String, password: String): String {
        return authRemoteDataSource.register(email, username, password)
    }

    override suspend fun saveToken(token: String) {
        authLocalDataSource.saveToken(token)
    }

    override suspend fun loadToken(): String? {
        return authLocalDataSource.loadToken()
    }

    override suspend fun clearToken() {
        authLocalDataSource.clearToken()
    }
}