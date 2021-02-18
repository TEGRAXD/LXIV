package com.suganda8.lxiv

import android.graphics.Bitmap
import com.suganda8.lxiv.Decoder.Companion.bitmap
import com.suganda8.lxiv.Decoder.Companion.byteArray

class DecoderBuilder {
    private var base64String: String? = null
    private var flag: Int? = null

    companion object {
        inline fun asBitmap(block: (Decoder) -> Unit): Bitmap = Decoder().apply(block).bitmap()
        inline fun asByteArray(block: (Decoder) -> Unit): ByteArray = Decoder().apply(block).byteArray()
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