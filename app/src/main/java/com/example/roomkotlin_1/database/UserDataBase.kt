package com.example.roomkotlin_1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomkotlin_1.dao.UserDao
import com.example.roomkotlin_1.entity.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UserDataBase :RoomDatabase() {
    abstract fun getUserDao(): UserDao
}