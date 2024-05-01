package com.anangkur.synrgychapter3.data.datasource

interface AuthLocalDataSource {
    fun saveToken(token: String)
    fun loadToken(): String?
    fun clearToken()
}