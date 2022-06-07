package com.zhang.utilslibiary.utils

import android.text.TextWatcher
import android.text.Editable

abstract class MyTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onMyTextChanged(s, start, before, count)
    }

    override fun afterTextChanged(s: Editable) {
    }

    abstract fun onMyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}