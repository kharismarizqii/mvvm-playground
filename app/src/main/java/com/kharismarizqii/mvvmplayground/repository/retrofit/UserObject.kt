package com.kharismarizqii.mvvmplayground.repository.retrofit

import com.google.gson.annotations.SerializedName

data class UserObject(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)