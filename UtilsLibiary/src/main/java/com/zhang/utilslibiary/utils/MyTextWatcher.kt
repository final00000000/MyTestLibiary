package com.zhang.utilslibiary.utils

import android.text.TextWatcher
import android.text.Editable

abstract class MyTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        beforeMyTextChanged(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onMyTextChanged(s, start, before, count)
    }

    override fun afterTextChanged(s: Editable) {
        afterMyTextChanged(s)
    }

    fun afterMyTextChanged(s: Editable?) {

    }

    fun beforeMyTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    abstract fun onMyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}