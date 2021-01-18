package com.example.roomkotlin_1.dao

import androidx.room.*
import com.example.roomkotlin_1.entity.Users

@Dao
interface UserDao {

    @Query("Select * from users")
    fun getUsers(): List<Users>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: Users)

    @Update
    fun updateUsers(users: Users)

    @Delete
    fun deleteUsers(users: Users)
}