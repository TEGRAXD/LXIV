package com.astaria.lxiv

import android.graphics.Bitmap

class LXIV {
    companion object {
        fun createEncoder() : EncoderBuilder.Companion = EncoderBuilder
        fun createDecoder() : DecoderBuilder.Companion = DecoderBuilder
    }

}