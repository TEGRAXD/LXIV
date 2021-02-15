package com.astaria.lxiv.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class Encoder(
    private val context: Context,
    private val uri: Uri,
    private val quality: Int,
    private val compressFormat: Bitmap.CompressFormat,
    private val encoderFlag: EncoderFlag? = null
) {
    fun base64() : String {
        val input = context.contentResolver.openInputStream(uri)
        val image = BitmapFactory.decodeStream(input, null, null) ?: throw NullPointerException()

        val baos = ByteArrayOutputStream()
        image.compress(compressFormat, quality, baos)
        val imageBytes = baos.toByteArray()

        return Base64.encodeToString(imageBytes, encoderFlag?.encoderFlag ?: EncoderFlag.DEFAULT.encoderFlag)
    }
}