package com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.request


import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)