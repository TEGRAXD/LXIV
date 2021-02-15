package com.astaria.lxiv.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class EncoderBuilder(
    private val context: Context
) : EncoderInterface {
    private var uri: Uri? = null
    private var quality: Int? = null
    private var compressFormat: Bitmap.CompressFormat? = null
    private var encoderFlag: EncoderFlag? = null

    override fun setUri(uri: Uri?): EncoderInterface {
        if (uri == null) throw IllegalArgumentException("Uri should not be null.")
        this.uri = uri
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

    override fun setEncoderFlag(encoderFlag: EncoderFlag): EncoderInterface {
        this.encoderFlag = encoderFlag
        return this
    }

    @Throws(IllegalArgumentException::class)
    override fun build(): String {
        return Encoder(context, uri ?: throw IllegalArgumentException("Uri should not be null."), quality ?: throw IllegalArgumentException("Quality should not be null."), compressFormat ?: throw IllegalArgumentException("Compress Format should not be null."), encoderFlag).base64()
    }
}