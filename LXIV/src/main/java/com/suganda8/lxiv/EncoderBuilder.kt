/*
 *    Copyright (C) 2021 Tegar Bangun Suganda, ASTARIA.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.suganda8.lxiv

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.suganda8.lxiv.Encoder.Companion.bitmapBase64
import com.suganda8.lxiv.Encoder.Companion.uriBase64

class EncoderBuilder(
    private val context: Context? = null
) {
    private var input: Any? = null
    private var quality: Int? = null
    private var compressFormat: Bitmap.CompressFormat? = null
    private var flag: Int? = null

    companion object {
        inline fun fromBitmap(context: Context, block: (Encoder) -> Unit): String = Encoder(context).apply(block).bitmapBase64()
        inline fun fromUri(context: Context, block: (Encoder) -> Unit): String = Encoder(context).apply(block).uriBase64()
    }

    fun setBitmap(bitmap: Bitmap?): EncoderBuilder {
        if (bitmap == null) throw IllegalArgumentException("Bitmap should not be null.")
        this.input = bitmap
        return this
    }

    fun setUri(uri: Uri?): EncoderBuilder {
        if (uri == null) throw IllegalArgumentException("Uri should not be null.")
        this.input = uri
        return this
    }

    fun setQuality(quality: Int): EncoderBuilder {
        if (quality < 0 || quality > 100) throw IllegalArgumentException("Quality should be around 0 to 100.")
        this.quality = quality
        return this
    }

    fun setCompressFormat(compressFormat: Bitmap.CompressFormat): EncoderBuilder {
        this.compressFormat = compressFormat
        return this
    }

    fun setFlag(flag: Int?): EncoderBuilder {
        this.flag = flag
        return this
    }

    fun build(): String {
        return when (input) {
            is Bitmap -> Encoder(context ?: throw IllegalArgumentException("Context should not be null."),
                input ?: throw IllegalArgumentException("Either Bitmap or Uri should not be null. Please use setBitmap or setUri method first."),
                quality ?: throw IllegalArgumentException("Quality should not be null."),
                compressFormat ?: throw IllegalArgumentException("Compress Format should not be null."),
                flag).bitmapBase64()
            is Uri -> Encoder(context ?: throw IllegalArgumentException("Context should not be null."),
                input ?: throw IllegalArgumentException("Either Bitmap or Uri should not be null. Please use setBitmap or setUri method first."),
                quality ?: throw IllegalArgumentException("Quality should not be null."),
                compressFormat ?: throw IllegalArgumentException("Compress Format should not be null."),
                flag).uriBase64()
            else -> throw IllegalArgumentException("Please use either method setBitmap or setUri.")
        }
    }
}