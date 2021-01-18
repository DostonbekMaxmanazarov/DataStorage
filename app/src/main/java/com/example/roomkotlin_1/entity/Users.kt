package com.example.roomkotlin_1.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Users(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var mId: Int = 0,
    @ColumnInfo(name = "user_name", typeAffinity = ColumnInfo.TEXT) var mUserName: String,
    @ColumnInfo(name = "full_name") var mFullName: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray
)