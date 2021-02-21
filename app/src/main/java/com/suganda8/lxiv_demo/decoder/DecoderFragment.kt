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

package com.suganda8.lxiv_demo.decoder

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.suganda8.lxiv.LXIV
import com.suganda8.lxiv_demo.R
import com.suganda8.lxiv_demo.databinding.FragmentDecoderBinding
import com.suganda8.lxiv_demo.encoder.EncoderFragment

class DecoderFragment : Fragment() {
    private lateinit var binding: FragmentDecoderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDecoderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    private fun setUpUI() {
        binding.btnDecoderCenterCropScaletypeFrDecoder.setOnClickListener {
            binding.imgvImageLoadedFrDecoder.scaleType = ImageView.ScaleType.CENTER_CROP
        }

        binding.btnDecoderFitCenterScaletypeFrDecoder.setOnClickListener {
            binding.imgvImageLoadedFrDecoder.scaleType = ImageView.ScaleType.FIT_CENTER
        }

        binding.btnDecoderCheckFrDecoder.setOnClickListener {
            if (binding.tietDecoderBase64StringFrDecoder.text.toString().isNotEmpty()) {
                try {
                    // Decode Base64 String
                    val bitmap = LXIV.createDecoder().asBitmap {
                        it.base64String = binding.tietDecoderBase64StringFrDecoder.text.toString()
                        it.flag = Base64.DEFAULT
                    }

                    // Load bitmap to ImageView
                    binding.imgvImageLoadedFrDecoder.setImageBitmap(bitmap)

                    binding.tvEncodeBase64LengthFrDecoder.text = binding.tietDecoderBase64StringFrDecoder.text.toString().length.toString()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DecoderFragment()
    }
}