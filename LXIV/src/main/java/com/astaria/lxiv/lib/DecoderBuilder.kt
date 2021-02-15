package com.astaria.lxiv.lib

import android.graphics.Bitmap

class DecoderBuilder: DecoderInterface {
    private var base64String: String? = null
    private var flag: Flag? = null
    // private var decoderOutput: DecoderOutput? = null

    override fun setBase64String(base64String: String?): DecoderInterface {
        if (base64String.isNullOrEmpty()) throw IllegalArgumentException("Base64 String should not be null.")
        this.base64String = base64String
        return this
    }

    override fun setFlag(flag: Flag): DecoderInterface {
        this.flag = flag
        return this
    }

    // override fun setAs(decoderOutput: DecoderOutput): DecoderInterface {
    //     this.decoderOutput = decoderOutput
    //     return this
    // }

    override fun buildAsBitmap(): Bitmap {
        return Decoder(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag).bitmap()
    }

    override fun buildAsByteArray(): ByteArray {
        return Decoder(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag).byteArray()
    }
}