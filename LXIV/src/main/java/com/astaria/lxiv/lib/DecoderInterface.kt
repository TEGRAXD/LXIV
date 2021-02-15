package com.astaria.lxiv.lib

import android.graphics.Bitmap

interface DecoderInterface {
    fun buildAsBitmap(): Bitmap
    fun buildAsByteArray(): ByteArray
    fun setBase64String(base64String: String?) : DecoderInterface
    fun setFlag(flag: Flag) : DecoderInterface
    // fun setAs(decoderOutput: DecoderOutput) : DecoderInterface
}