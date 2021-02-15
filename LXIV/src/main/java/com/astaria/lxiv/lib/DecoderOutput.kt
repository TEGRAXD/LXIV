package com.astaria.lxiv.lib

import android.util.Base64
import android.util.Base64OutputStream

enum class DecoderOutput(val outputAs: Int) {

    /**
     * Default values for encoder/decoder flags.
     */
    BITMAP(1),

    /**
     * Default values for encoder/decoder flags.
     */
    BYTE_ARRAY(2)
}