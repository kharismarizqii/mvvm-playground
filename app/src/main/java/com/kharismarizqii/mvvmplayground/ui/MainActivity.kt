package com.kharismarizqii.mvvmplayground.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kharismarizqii.mvvmplayground.R
import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.repository.local.CacheMapper
import com.kharismarizqii.mvvmplayground.repository.local.UserCache
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: MainViewModel
    private val cacheMapper: CacheMapper = CacheMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = UserAdapter()
        rv_user.setHasFixedSize(true)
        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.adapter = adapter
        if (checkConnectivity()) {
            viewModel.getAll().observe(this@MainActivity, object : Observer<ArrayList<User>> {
                override fun onChanged(t: ArrayList<User>?) {
                    if (t != null) {
                        adapter.setUser(t)
                    }

                }
            })
        }else{
            viewModel.getAllFromCache().observe(this@MainActivity, object : Observer<List<UserCache>>{
                override fun onChanged(t: List<UserCache>?) {
                    val list = t.let { cacheMapper.mapFromEntityList(it as ArrayList<UserCache>) }
                    adapter.setUser(list)
                }

            })
        }

    }

    private fun checkConnectivity(): Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if (!isConnected){
            Toast.makeText(this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "ADA INTERNET CONNECTION", Toast.LENGTH_SHORT).show()
        }
        return isConnected
    }

}
