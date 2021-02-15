package com.astaria.lxiv.lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class Decoder(
    private val base64String: String,
    private val flag: Flag? = null
) {
    fun bitmap() : Bitmap {
        val imageBytes = Base64.decode(base64String, flag?.encoderFlag ?: Flag.DEFAULT.encoderFlag)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun byteArray() : ByteArray {
        return Base64.decode(base64String, flag?.encoderFlag ?: Flag.DEFAULT.encoderFlag)
    }
}