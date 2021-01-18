package com.example.roomkotlin_1.roomapp

import android.app.Application
import androidx.room.Room
import com.example.roomkotlin_1.database.UserDataBase

class UserApp : Application() {
    private lateinit var userDataBase: UserDataBase

    companion object {
        lateinit var userApp: UserApp
    }

    override fun onCreate() {
        super.onCreate()
        userApp = this
        userDataBase = Room.databaseBuilder(this, UserDataBase::class.java, "userdb")
            .allowMainThreadQueries().build()
    }

    fun getUserDataBase(): UserDataBase {
        return userDataBase
    }
}