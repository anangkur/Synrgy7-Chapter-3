package com.anangkur.synrgychapter3.data.datasource.remote

import com.anangkur.synrgychapter3.data.datasource.AuthRemoteDataSource
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.ReqresService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.request.LoginBody
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
        return reqresService.login(
            loginBody = LoginBody(
                email = username,
                password = password,
                username = username,
            )
        ).token
    }
}