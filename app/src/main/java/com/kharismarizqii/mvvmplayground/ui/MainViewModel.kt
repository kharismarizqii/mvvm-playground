package com.kharismarizqii.mvvmplayground.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.repository.MainRepository

class MainViewModel : ViewModel(){
    private var repository : MainRepository = MainRepository()
    private var list: LiveData<List<User>> = MutableLiveData<List<User>>()

    fun getAll(): LiveData<ArrayList<User>> {
        return repository.getUsers()
    }
}