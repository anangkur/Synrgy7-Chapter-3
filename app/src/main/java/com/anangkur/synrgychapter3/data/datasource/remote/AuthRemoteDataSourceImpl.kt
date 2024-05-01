package com.anangkur.synrgychapter3.data.datasource.remote

import com.anangkur.synrgychapter3.data.datasource.AuthRemoteDataSource
import com.anangkur.synrgychapter3.data.model.User
import kotlinx.coroutines.delay

class AuthRemoteDataSourceImpl : AuthRemoteDataSource {

    private val users = listOf(
        User(
            userName = "anangkur",
            password = "anangkur123",
        ),
    )

    override suspend fun login(username: String, password: String): String {
        delay(1000)
        if (users.contains(User(username, password))){
            return "abcdefghijklmnopqrstuvwxyz0987654321"
        } else {
            throw UnsupportedOperationException("user tidak ditemukan")
        }
    }
}