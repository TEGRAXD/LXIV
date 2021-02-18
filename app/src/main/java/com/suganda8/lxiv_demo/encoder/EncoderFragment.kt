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

package com.suganda8.lxiv_demo.encoder

import android.app.Activity.RESULT_OK
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.suganda8.lxiv.LXIV
import com.suganda8.lxiv_demo.R
import com.suganda8.lxiv_demo.databinding.FragmentEncoderBinding

class EncoderFragment : Fragment() {
    private lateinit var binding: FragmentEncoderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEncoderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    private fun setUpUI() {
        binding.btnEncoderCenterCropScaletypeFrEncoder.setOnClickListener {
            binding.imgvLoadedImageFrEncoder.scaleType = ImageView.ScaleType.CENTER_CROP
        }

        binding.btnEncoderFitCenterScaletypeFrEncoder.setOnClickListener {
            binding.imgvLoadedImageFrEncoder.scaleType = ImageView.ScaleType.FIT_CENTER
        }

        binding.btnEncoderLoadAnImageFrEncoder.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST_CODE)
        }

        binding.tilEncoderBase64StringFrEncoder.setEndIconOnClickListener {
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("base64 encode", binding.tietEncoderBase64StringFrEncoder.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PHOTO_PICKER_REQUEST_CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    // Get uri from intent's data
                    val uri = data.data
                    // Set image from uri
                    binding.imgvLoadedImageFrEncoder.setImageURI(uri)

                    // Encode Uri (You should do this in background)
                    val base64EncodedString = LXIV.createEncoder().fromUri(requireContext()) {
                        it.input = uri
                        it.quality = 75
                        it.compressFormat = Bitmap.CompressFormat.JPEG
                        it.flag = Base64.DEFAULT
                    }

                    binding.tietEncoderBase64StringFrEncoder.setText(base64EncodedString)
                    binding.tvEncodeBase64LengthFrEncoder.text = base64EncodedString.length.toString()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EncoderFragment()
        const val PHOTO_PICKER_REQUEST_CODE = 8
    }
}