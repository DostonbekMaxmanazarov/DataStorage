package com.example.roomkotlin_1.convert

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class ImageConvert {

    companion object {


        fun convertImageArrayByte(bitmap: Bitmap): ByteArray {


            var byteArrayBitmap = ByteArrayOutputStream()

              var  bitmap2 = Bitmap.createScaledBitmap(bitmap, 256, 256, false)
                bitmap2.compress(Bitmap.CompressFormat.PNG, 0, byteArrayBitmap)

            return byteArrayBitmap.toByteArray()

        }

        fun convertByteImageArray(array: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(array, 0, array.size)
        }
    }

}