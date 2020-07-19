package com.kharismarizqii.mvvmplayground.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.repository.MainRepository
import com.kharismarizqii.mvvmplayground.repository.local.CacheMapper
import com.kharismarizqii.mvvmplayground.repository.local.UserCache

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: MainRepository = MainRepository(application)
    private var systemService = application.getSystemService(Context.CONNECTIVITY_SERVICE)
    private var list: LiveData<List<User>> = MutableLiveData<List<User>>()
    private val cacheMapper: CacheMapper = CacheMapper()

    fun getAll(): LiveData<ArrayList<User>> {
        return repository.getUsers()
    }

    fun getAllFromCache(): LiveData<List<UserCache>>{
        return repository.getCache()
    }
}