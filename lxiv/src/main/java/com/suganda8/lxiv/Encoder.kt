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

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class Encoder(
    private val context: Context,
    var input: Any? = null,
    var quality: Int? = null,
    var compressFormat: Bitmap.CompressFormat? = null,
    var flag: Int? = null
) {
    companion object {
        fun Encoder.bitmapBase64() : String {
            if (input == null) throw IllegalArgumentException("Set input source first from either Bitmap or Uri.")
            if (input !is Bitmap) throw IllegalArgumentException("Set input source from Bitmap.")

            val baos = ByteArrayOutputStream()
            (input as Bitmap).compress(compressFormat, quality ?: throw IllegalArgumentException("Quality should not be null."), baos)
            val imageBytes = baos.toByteArray()

            return Base64.encodeToString(imageBytes, flag ?: Base64.DEFAULT)
        }

        fun Encoder.uriBase64() : String {
            if (input == null) throw IllegalArgumentException("Set input source first from either Bitmap or Uri.")
            if (input !is Uri) throw IllegalArgumentException("Set input source from Uri.")

            val inputStream = context.contentResolver.openInputStream(input as Uri)
            val imageBitmap = BitmapFactory.decodeStream(inputStream, null, null) ?: throw NullPointerException()

            val baos = ByteArrayOutputStream()
            imageBitmap.compress(compressFormat, quality ?: throw IllegalArgumentException("Quality should not be null."), baos)
            val imageByteArray = baos.toByteArray()

            return Base64.encodeToString(imageByteArray, flag ?: Base64.DEFAULT)
        }
    }
}