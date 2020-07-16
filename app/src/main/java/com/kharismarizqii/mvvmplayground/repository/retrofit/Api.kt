package com.kharismarizqii.mvvmplayground.repository.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("users")
    fun get(): Call<UserReponse>
}