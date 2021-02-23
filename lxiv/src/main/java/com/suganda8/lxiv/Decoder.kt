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

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.util.*

class Decoder(
    var base64String: String? = null,
    var flag: Int? = null
) {
    companion object {
        fun Decoder.bitmap(): Decoder {
            return Decoder(base64String, flag)
//            val imageBytes = Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag ?: Base64.DEFAULT)
//            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

        fun Decoder.bitmapExecute(): Bitmap {
            val imageBytes = Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."), flag ?: Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

        fun Decoder.byteArray(): ByteArray {
            return Base64.decode(base64String ?: throw IllegalArgumentException("Base64 String should not be null."),flag ?: Base64.DEFAULT)
        }

        @Suppress("DEPRECATION")
        fun Bitmap.save(context: Context, absolutePath: String?, name: String, compressFormat: Bitmap.CompressFormat, quality: Int): Boolean {
            if (name.contains(Regex("[?\":|<>\\\\/]"))) throw IllegalArgumentException("Name should not contain any of the following characters: \\/:*?\"<>|")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                println("SDK INT - LEBIH ATAU SAMA DENGAN DARI VERSION CODE Q")
                val imageContentValues = ContentValues()
                imageContentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + context.applicationInfo.loadLabel(context.packageManager).toString())
                imageContentValues.put(MediaStore.Images.ImageColumns.TITLE, name)
                imageContentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, name)
                imageContentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, when (compressFormat) {
                    Bitmap.CompressFormat.JPEG -> "image/jpeg"
                    Bitmap.CompressFormat.PNG -> "image/png"
                    else -> "image/webp"
                })
                imageContentValues.put(MediaStore.Images.Media.IS_PENDING, true)

                val imageUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageContentValues) ?: throw NullPointerException()

                val bitmap = this
                val outputStream = context.contentResolver.openOutputStream(imageUri)
                bitmap.compress(compressFormat, quality, outputStream)
                outputStream?.close()
                imageContentValues.put(MediaStore.Images.Media.IS_PENDING, false)
                context.contentResolver.update(imageUri, imageContentValues, null, null)
                return true
            } else {
                println("SDK INT - KURANG DARI VERSION CODE Q")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    println("SDK INT - LEBIH ATAU SAMA DENGAN DARI VERSION CODE M")
                    return if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + context.applicationInfo.loadLabel(context.packageManager).toString())
                        if (!directory.exists()) directory.mkdirs()
                        val file = File(directory.absolutePath, "$name.${
                            when (compressFormat) {
                                Bitmap.CompressFormat.JPEG -> "jpg"
                                Bitmap.CompressFormat.PNG -> "png"
                                else -> "webp"
                            }
                        }")
                        val bitmap = this
                        val os = context.contentResolver.openOutputStream(file.toUri())
                        bitmap.compress(compressFormat, quality, os)
                        os?.close()
                        MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), arrayOf("*/*"), null)
                        true
                    } else {
                        println("ELSE - SDK INT >= VERSION_CODES M")
                        false
                    }
                } else {
                    println("SDK INT - KURANG DARI VERSION CODE M")
                    return if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + context.applicationInfo.loadLabel(context.packageManager).toString())
                        if (!directory.exists()) directory.mkdirs()
                        val file = File(directory.absolutePath, "${name}.${
                            when (compressFormat) {
                                Bitmap.CompressFormat.JPEG -> "jpg"
                                Bitmap.CompressFormat.PNG -> "png"
                                else -> "webp"
                            }
                        }")
                        val bitmap = this
                        // val os = context.contentResolver.openOutputStream(file.toUri())
                        val os = FileOutputStream(file)
                        bitmap.compress(compressFormat, quality, os)
                        os.close()
                        MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), arrayOf("*/*"), null)
                        true
                    } else {
                        println("ELSE - SDK INT KURANG DARI VERSION_CODES M")
                        false
                    }
                }
            }
            // return false
        }
    }
}