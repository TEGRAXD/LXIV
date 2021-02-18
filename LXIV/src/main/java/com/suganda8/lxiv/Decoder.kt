package com.suganda8.lxiv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

open class Decoder(
    var base64String: String? = null,
    var flag: Int? = null
) {

    companion object {
        fun Decoder.bitmap() : Bitmap {
            val imageBytes = Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag ?: Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

        fun Decoder.byteArray() : ByteArray {
            return Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."),flag ?: Base64.DEFAULT)
        }
    }

//    private fun DecoderBuilder.bitmap() : Bitmap {
//        val imageBytes = Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag ?: Base64.DEFAULT)
//        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//    }

//    protected fun byteArray() : ByteArray = Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."),flag ?: Base64.DEFAULT)
}