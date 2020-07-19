package com.kharismarizqii.mvvmplayground.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.repository.local.CacheMapper
import com.kharismarizqii.mvvmplayground.repository.local.UserCache
import com.kharismarizqii.mvvmplayground.repository.local.UserDao
import com.kharismarizqii.mvvmplayground.repository.local.UserDatabase
import com.kharismarizqii.mvvmplayground.repository.retrofit.Api
import com.kharismarizqii.mvvmplayground.repository.retrofit.NetworkMapper
import com.kharismarizqii.mvvmplayground.repository.retrofit.RetrofitClient
import com.kharismarizqii.mvvmplayground.repository.retrofit.UserReponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(application: Application){
    private var userDao: UserDao?
    private var userDb: UserDatabase?
    private var api : Api
    private val cacheMapper: CacheMapper = CacheMapper()
    private val networkMapper: NetworkMapper = NetworkMapper()
    private var listUsers = MutableLiveData<ArrayList<User>>()
    private var app = application

    init {
        api = RetrofitClient.instance
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.userDao()
    }
    fun getUsers(): LiveData<ArrayList<User>> {
        api.get().enqueue(object: Callback<UserReponse>{
            override fun onFailure(call: Call<UserReponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserReponse>, response: Response<UserReponse>) {
                val listAfterMap = response.body()?.data?.let { networkMapper.mapFromEntityList(it) }
                if (listAfterMap != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        for (user in listAfterMap){
                            userDao?.insert(cacheMapper.mapToEntity(user))
                        }
                    }
                    listUsers.value = listAfterMap
                }

            }

        })
        return listUsers
    }

    fun getCache(): LiveData<List<UserCache>> {
        return userDao?.get()!!
    }
}