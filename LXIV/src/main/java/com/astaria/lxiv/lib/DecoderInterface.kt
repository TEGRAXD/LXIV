package com.astaria.lxiv.lib

import android.graphics.Bitmap
import android.net.Uri

interface DecoderInterface {
    fun build(): Bitmap
    fun setBase64String(base64String: String?) : DecoderInterface
    fun setEncoderFlag(encoderFlag: EncoderFlag) : DecoderInterface
}