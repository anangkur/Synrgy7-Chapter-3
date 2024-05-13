package com.anangkur.synrgychapter3.data.datasource

interface AuthLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun loadToken(): String?
    suspend fun clearToken()
}