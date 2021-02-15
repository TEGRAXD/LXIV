package com.astaria.lxiv.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class Decoder(
    private val base64String: String,
    private val encoderFlag: EncoderFlag? = null
) {
    fun bitmap() : Bitmap {
        val imageBytes = Base64.decode(base64String, encoderFlag?.encoderFlag ?: EncoderFlag.DEFAULT.encoderFlag)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}