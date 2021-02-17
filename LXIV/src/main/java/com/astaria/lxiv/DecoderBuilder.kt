package com.astaria.lxiv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class DecoderBuilder {
    private var base64String: String? = null
    private var flag: Int? = null

    companion object {
        inline fun asBitmap(block: (Decoder) -> Unit): Bitmap = Decoder().apply(block).bitmap()
        inline fun asByteArray(block: (Decoder) -> Unit): ByteArray = Decoder().apply(block).byteArray()

        fun Decoder.bitmap() : Bitmap {
            val imageBytes = Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag ?: Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

        fun Decoder.byteArray() : ByteArray {
            return Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."),flag ?: Base64.DEFAULT)
        }
    }

    fun setBase64String(base64String: String?): DecoderBuilder {
        if (base64String.isNullOrEmpty()) throw IllegalArgumentException("Base64 String should not be null.")
        this.base64String = base64String
        return this
    }

    fun setFlag(flag: Int?): DecoderBuilder {
        this.flag = flag
        return this
    }

    fun buildAsBitmap(): Bitmap {
        return Decoder(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag).bitmap()
    }

    fun buildAsByteArray(): ByteArray {
        return Decoder(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag).byteArray()
    }
}