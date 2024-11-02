package com.fabiandev.roadmapai.ui

import android.text.SpannableString
import android.text.style.UnderlineSpan

fun getUnderlinedText(text: String): SpannableString {
    val underlinedText = SpannableString(text)
    underlinedText.setSpan(UnderlineSpan(), 0, text.length, 0)
    return underlinedText
}