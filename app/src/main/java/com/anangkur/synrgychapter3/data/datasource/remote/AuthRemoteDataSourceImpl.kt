package com.anangkur.synrgychapter3.data.datasource.remote

import com.anangkur.synrgychapter3.data.datasource.AuthRemoteDataSource
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.ReqresService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.request.LoginBody
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.request.RegisterBody
import com.anangkur.synrgychapter3.data.model.User
import kotlinx.coroutines.delay

class AuthRemoteDataSourceImpl(
    private val reqresService: ReqresService,
) : AuthRemoteDataSource {

    private val users
        get() = listOf(
            User(
                userName = "anangkur",
                password = "anangkur123",
            ),
    )

    override suspend fun login(username: String, password: String): String {
        return if (users.contains(User(username, password))){
            "abcdefghijklmnopqrstuvwxyz0987654321"
        } else {
            reqresService.login(
                loginBody = LoginBody(
                    email = username,
                    password = password,
                    username = username,
                )
            ).token
        }
    }

    override suspend fun register(email: String, username: String, password: String): String {
        return reqresService.register(
            RegisterBody(
                email = email,
                username = username,
                password = password,
            )
        ).token
    }
}