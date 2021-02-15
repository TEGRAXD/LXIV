package com.astaria.lxiv.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class Encoder(
    private val context: Context,
    private val input: Any,
    private val quality: Int,
    private val compressFormat: Bitmap.CompressFormat,
    private val flag: Flag? = null
) {
    fun bitmapBase64() : String {
        if (input !is Bitmap) throw IllegalArgumentException("Set Bitmap.")
        val baos = ByteArrayOutputStream()
        input.compress(compressFormat, quality, baos)
        val imageBytes = baos.toByteArray()

        return Base64.encodeToString(imageBytes, flag?.encoderFlag ?: Flag.DEFAULT.encoderFlag)
    }

    fun uriBase64() : String {
        if (input !is Uri) throw IllegalArgumentException("Set Drawable.")
        val inputStream = context.contentResolver.openInputStream(input)
        val imageBitmap = BitmapFactory.decodeStream(inputStream, null, null) ?: throw NullPointerException()

        val baos = ByteArrayOutputStream()
        imageBitmap.compress(compressFormat, quality, baos)
        val imageByteArray = baos.toByteArray()

        return Base64.encodeToString(imageByteArray, flag?.encoderFlag ?: Flag.DEFAULT.encoderFlag)
    }
}