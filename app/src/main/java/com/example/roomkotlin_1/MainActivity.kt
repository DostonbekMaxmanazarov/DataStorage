package com.example.roomkotlin_1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.roomkotlin_1.convert.ImageConvert
import com.example.roomkotlin_1.dao.UserDao
import com.example.roomkotlin_1.entity.Users
import com.example.roomkotlin_1.roomapp.UserApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CAMERA_CODE = 100
    private val GALLERY_CODE = 101
    private var bitmapImage: Bitmap? = null
    private lateinit var userDao: UserDao

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val color = ContextCompat.getColor(this, R.color.statusColor)

        window.statusBarColor = color

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }


        bitmapImage = null
        userDao = UserApp.userApp.getUserDataBase().getUserDao()

        bt_camera.setOnClickListener {
            getGallery(it)
        }
        bt_gallery.setOnClickListener {
            getCamera(it)
        }
        bt_save.setOnClickListener {
            saveUser(it)
        }
        bt_show.setOnClickListener {
            showUser(it)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getGallery(it: View) {
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, GALLERY_CODE)
        }
    }

    fun getCamera(view: View) {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA_CODE)
        }
    }

    fun saveUser(view: View) {

        if (edit_fullName.text.isEmpty() ||
            edit_userName.text.isEmpty() ||
            edit_date.text.isEmpty() ||
            edit_password.text.isEmpty() || bitmapImage == null

        ) {
            val dialog = AlertDialog.Builder(this)
            dialog.setPositiveButton("Ok", null)
                .setTitle("Xabar")
                .setMessage("Iltimos ma'lumotlarni to'ldiring")
                .show()
        } else {

            var user = Users(
                mUserName = edit_userName.text.toString(),
                mFullName = edit_fullName.text.toString(),
                image = ImageConvert.convertImageArrayByte(bitmapImage!!)


            )
            userDao.insertUsers(user)

            Toast.makeText(this, "Good", Toast.LENGTH_LONG).show()
            edit_fullName.setText("")
            edit_date.setText("")
            edit_password.setText("")
            edit_userName.setText("")
            image_camera.setImageBitmap(null)
            bitmapImage == null

        }
    }

    fun showUser(view: View) {

        var intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            GALLERY_CODE -> {
                if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK) {
                    val contentUri = data!!.data
                    bitmapImage =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, contentUri)
                    image_camera.setImageBitmap(bitmapImage)
                }
            }

            CAMERA_CODE -> {
                if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_CODE) {
                    bitmapImage = data!!.extras["data"] as Bitmap
                    if (bitmapImage != null) {
                        image_camera.setImageBitmap(bitmapImage)
                        Toast.makeText(
                            this,
                            "height->" + bitmapImage!!.height + " weidht->" + bitmapImage!!.width,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(this, "bitmap null", Toast.LENGTH_LONG).show()
                    }
                    Toast.makeText(
                        this,
                        "requestCode $requestCode -> resultCode $resultCode",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(this, "requestCode not code", Toast.LENGTH_LONG).show()
                }

                return
            }
        }
    }

}
