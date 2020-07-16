package com.kharismarizqii.mvvmplayground.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kharismarizqii.mvvmplayground.R
import com.kharismarizqii.mvvmplayground.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = UserAdapter()

        rv_user.setHasFixedSize(true)
        rv_user.layoutManager = LinearLayoutManager(this)
        viewModel.getAll().observe(this@MainActivity, object : Observer<ArrayList<User>> {
            override fun onChanged(t: ArrayList<User>?) {
                if (t != null) {
                    adapter.setUser(t)
                }
                rv_user.adapter = adapter
            }

        })
    }

}
