package com.anangkur.synrgychapter3.data.datasource.remote.retrofit

import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.request.LoginBody
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresService {

    @POST("login")
    suspend fun login(
        @Body loginBody: LoginBody,
    ): LoginResponse
}