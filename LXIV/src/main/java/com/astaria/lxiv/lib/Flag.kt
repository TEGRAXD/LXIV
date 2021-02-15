package com.astaria.lxiv.lib

import android.util.Base64
import android.util.Base64OutputStream

enum class Flag(val encoderFlag: Int) {

    // Base64 Class All Rights Reserved By Android.

    /**
     * Default values for encoder/decoder flags.
     */
    DEFAULT(Base64.DEFAULT),

    /**
     * Encoder flag bit to omit the padding '=' characters at the end
     * of the output (if any).
     */
    NO_PADDING(Base64.NO_PADDING),

    /**
     * Encoder flag bit to omit all line terminators (i.e., the output
     * will be on one long line).
     */
    NO_WRAP(Base64.NO_WRAP),

    /**
     * Encoder flag bit to indicate lines should be terminated with a
     * CRLF pair instead of just an LF.  Has no effect if `NO_WRAP` is specified as well.
     */
    CRLF(Base64.CRLF),

    /**
     * Encoder/decoder flag bit to indicate using the "URL and
     * filename safe" variant of Base64 (see RFC 3548 section 4) where
     * `-` and `_` are used in place of `+` and
     * `/`.
     */
    URL_SAFE(Base64.URL_SAFE),

    /**
     * Flag to pass to [Base64OutputStream] to indicate that it
     * should not close the output stream it is wrapping when it
     * itself is closed.
     */
    NO_CLOSE(Base64.NO_CLOSE)
}