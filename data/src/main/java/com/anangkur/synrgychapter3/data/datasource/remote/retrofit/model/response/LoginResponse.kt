package com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("token")
    val token: String
)