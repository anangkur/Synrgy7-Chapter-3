package com.anangkur.synrgychapter3.domain

interface AuthRepository {
    suspend fun login(username: String, password: String): String
    suspend fun register(username: String, email: String, password: String): String
    suspend fun saveToken(token: String)
    suspend fun loadToken(): String?
    suspend fun clearToken()
}