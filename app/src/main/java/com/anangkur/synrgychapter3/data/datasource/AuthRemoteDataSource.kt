package com.anangkur.synrgychapter3.data.datasource

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): String
}