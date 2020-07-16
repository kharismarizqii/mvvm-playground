package com.kharismarizqii.mvvmplayground.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.mvvmplayground.R
import com.kharismarizqii.mvvmplayground.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var users = ArrayList<User>()
    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(user: User){
            with(itemView){
                val name = "${user.firstName} ${user.lastName}"
                tv_name.text = name
                tvEmail.text = user.email
                Picasso.get().load(user.avatar).into(iv_ava)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun setUser(list: ArrayList<User>){
        users = list
        notifyDataSetChanged()
    }
}