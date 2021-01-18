package com.example.roomkotlin_1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomkotlin_1.adapter.AdapterView
import com.example.roomkotlin_1.dao.UserDao
import com.example.roomkotlin_1.roomapp.UserApp
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var adapterView: AdapterView
    private lateinit var userDao: UserDao

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val color = ContextCompat.getColor(this, R.color.statusColor)

        window.statusBarColor = color

        userDao = UserApp.userApp.getUserDataBase().getUserDao()
        adapterView = AdapterView(userDao.getUsers())
        rv.adapter = adapterView
        rv.layoutManager = LinearLayoutManager(this)
        adapterView.notifyDataSetChanged()

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
