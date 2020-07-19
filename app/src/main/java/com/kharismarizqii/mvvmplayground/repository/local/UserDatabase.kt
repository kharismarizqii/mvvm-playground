package com.kharismarizqii.mvvmplayground.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserCache::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
        val DATABASE_NAME = "user_db"

        var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase?{
            if(INSTANCE ==null){
                synchronized(UserDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserDatabase::class.java,
                        DATABASE_NAME).build()
                }
            }
            return INSTANCE
        }
    }
}