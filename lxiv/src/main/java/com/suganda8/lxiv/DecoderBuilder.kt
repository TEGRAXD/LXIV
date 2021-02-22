/*
 *    Copyright 2021 Tegar Bangun Suganda, ASTARIA.
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