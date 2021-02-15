package com.astaria.lxiv.lib

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

class EncoderBuilder(
    private val context: Context
) : EncoderInterface {
    private var input: Any? = null
    private var quality: Int? = null
    private var compressFormat: Bitmap.CompressFormat? = null
    private var flag: Flag? = null

    override fun setBitmap(bitmap: Bitmap?): EncoderInterface {
        if (bitmap == null) throw IllegalArgumentException("Bitmap should not be null.")
        this.input = bitmap
        return this
    }

    override fun setUri(uri: Uri?): EncoderInterface {
        if (uri == null) throw IllegalArgumentException("Uri should not be null.")
        this.input = uri
        return this
    }

    override fun setQuality(quality: Int): EncoderInterface {
        if (quality < 0 || quality > 100) throw IllegalArgumentException("Quality should be around 0 to 100.")
        this.quality = quality
        return this
    }

    override fun setCompressFormat(compressFormat: Bitmap.CompressFormat): EncoderInterface {
        this.compressFormat = compressFormat
        return this
    }

    override fun setFlag(flag: Flag): EncoderInterface {
        this.flag = flag
        return this
    }

    override fun build(): String {
        return when (input) {
            is Bitmap -> Encoder(context,
                input ?: throw IllegalArgumentException("Either Bitmap or Uri should not be null. Please use setBitmap or setUri method first."),
                quality ?: throw IllegalArgumentException("Quality should not be null."),
                compressFormat ?: throw IllegalArgumentException("Compress Format should not be null."),
                flag).bitmapBase64()
            is Uri -> Encoder(context,
                input ?: throw IllegalArgumentException("Either Bitmap or Uri should not be null. Please use setBitmap or setUri method first."),
                quality ?: throw IllegalArgumentException("Quality should not be null."),
                compressFormat ?: throw IllegalArgumentException("Compress Format should not be null."),
                flag).uriBase64()
            else -> throw IllegalArgumentException("Please use either method setBitmap or setUri.")
        }

    }
}