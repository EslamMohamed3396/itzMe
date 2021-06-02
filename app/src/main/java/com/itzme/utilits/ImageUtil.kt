package com.itzme.utilits

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream


object ImageUtil {

    fun encodeImage(bm: Bitmap?): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bm?.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }



    fun setUrlImage(imageView: ImageView, imgUrl: String?) {
        if (imgUrl != null) {
            val data = Base64.decode(imgUrl, Base64.DEFAULT)
            val image = BitmapFactory.decodeByteArray(data, 0, data.size)
            imageView.setImageBitmap(image)
        }
    }


}