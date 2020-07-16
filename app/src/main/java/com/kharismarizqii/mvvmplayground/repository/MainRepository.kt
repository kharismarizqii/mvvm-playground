package com.kharismarizqii.mvvmplayground.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.repository.retrofit.Api
import com.kharismarizqii.mvvmplayground.repository.retrofit.NetworkMapper
import com.kharismarizqii.mvvmplayground.repository.retrofit.RetrofitClient
import com.kharismarizqii.mvvmplayground.repository.retrofit.UserReponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository{
    private var api : Api
    private val networkMapper: NetworkMapper = NetworkMapper()
    private var listUsers = MutableLiveData<ArrayList<User>>()

    init {
        api = RetrofitClient.instance
    }
    fun getUsers(): LiveData<ArrayList<User>> {
        api.get().enqueue(object: Callback<UserReponse>{
            override fun onFailure(call: Call<UserReponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserReponse>, response: Response<UserReponse>) {
                val listAfterMap = response.body()?.data?.let { networkMapper.mapFromEntityList(it) }
                listUsers.value = listAfterMap
            }

        })
        return listUsers
    }
}