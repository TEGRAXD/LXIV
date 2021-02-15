package com.astaria.lxiv.lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class DecoderBuilder : DecoderInterface {
    private var base64String: String? = null
    private var encoderFlag: EncoderFlag? = null

//    private fun buildingDecoder(): Bitmap {
//        if (base64String.isEmpty()) throw IllegalArgumentException()
//        val imageBytes = Base64.decode(base64String, encoderFlag?.encoderFlag ?: EncoderFlag.DEFAULT.encoderFlag)
//        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//    }

    override fun setBase64String(base64String: String?): DecoderInterface {
        if (base64String.isNullOrEmpty()) throw IllegalArgumentException("Uri should not be null.")
        this.base64String = base64String
        return this
    }

    override fun setEncoderFlag(encoderFlag: EncoderFlag): DecoderInterface {
        this.encoderFlag = encoderFlag
        return this
    }

    override fun build(): Bitmap {
        return Decoder(base64String ?: throw IllegalArgumentException("Base64String should not be null."), encoderFlag).bitmap()
    }
}