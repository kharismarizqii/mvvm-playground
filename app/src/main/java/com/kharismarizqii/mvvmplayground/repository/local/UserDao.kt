package com.kharismarizqii.mvvmplayground.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (user: UserCache): Long

    @Query("SELECT * FROM users")
    fun get(): LiveData<List<UserCache>>
}