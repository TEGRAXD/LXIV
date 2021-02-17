package com.astaria.lxiv

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class Encoder(
    val context: Context,
    var input: Any? = null,
    var quality: Int? = null,
    var compressFormat: Bitmap.CompressFormat? = null,
    var flag: Int? = null
) {

//    fun bitmapBase64() : String {
//        if (input == null) throw IllegalArgumentException("Set input source first from either Bitmap or Uri.")
//        if (input !is Bitmap) throw IllegalArgumentException("Set input source from Bitmap.")
//        val baos = ByteArrayOutputStream()
//        input.compress(compressFormat, quality ?: throw IllegalArgumentException("Quality should not be null."), baos)
//        val imageBytes = baos.toByteArray()
//
//        return Base64.encodeToString(imageBytes, flag ?: Base64.DEFAULT)
//    }
//
//    fun uriBase64() : String {
//        if (input == null) throw IllegalArgumentException("Set input source first from either Bitmap or Uri.")
//        if (input !is Uri) throw IllegalArgumentException("Set Drawable.")
//        val inputStream = context.contentResolver.openInputStream(input)
//        val imageBitmap = BitmapFactory.decodeStream(inputStream, null, null) ?: throw NullPointerException()
//
//        val baos = ByteArrayOutputStream()
//        imageBitmap.compress(compressFormat, quality ?: throw IllegalArgumentException("Quality should not be null."), baos)
//        val imageByteArray = baos.toByteArray()
//
//        return Base64.encodeToString(imageByteArray, flag ?: Base64.DEFAULT)
//    }
}