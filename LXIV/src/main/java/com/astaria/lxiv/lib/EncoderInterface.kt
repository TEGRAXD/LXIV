package com.astaria.lxiv.lib

import android.graphics.Bitmap
import android.net.Uri

interface EncoderInterface {
    fun build(): String
    fun setBitmap(bitmap: Bitmap?) : EncoderInterface
    fun setUri(uri: Uri?) : EncoderInterface
    fun setQuality(quality: Int) : EncoderInterface
    fun setCompressFormat(compressFormat: Bitmap.CompressFormat) : EncoderInterface
    fun setFlag(flag: Flag) : EncoderInterface
}