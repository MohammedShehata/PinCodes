package com.sh.pincodes

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import java.lang.StringBuilder

class PinCodes private constructor(
    private val pinsViews: Array<out EditText>,
    private val onPinsCompleteListener: OnPinsCompleteListener
) : View.OnKeyListener {

    companion object {

        fun newInstance(
            onPinsCompleteListener: OnPinsCompleteListener,
            vararg pinsViews: EditText
        ): PinCodes {
            return PinCodes(pinsViews, onPinsCompleteListener)
        }
    }

    init {
        for ((index, pin) in pinsViews.withIndex()) {

            pin.setOnKeyListener(this)

            pin.addTextChangedListener(object : CustomTextWatcher() {
                override fun onTextChanged(char: Char, trailingText: String?) {
                    val nextPin = if (index + 1 < pinsViews.size) {
                        pinsViews[index + 1]
                    } else {
                        null
                    }
                    onTextChanged(pin, nextPin, char, trailingText)
                }
            })
        }
    }

    private fun getPinsCode(): String {
        val sb = StringBuilder("")
        for (pin in pinsViews) {
            if (pin.text.toString().isNotEmpty()) {
                sb.append(pin.text.toString())
            }
        }
        return sb.toString()
    }

    private fun onTextChanged(
        view1: EditText,
        view2: EditText?,
        char: Char,
        trailingText: String?
    ) {
        if (view1.text.toString() != char.toString()) {
            view1.setText(char.toString())
        }
        if (trailingText != null && view2 != null) {
            view2.requestFocus()
            view2.setText(trailingText)
        }
    }

    override fun onKey(view: View, keyCode: Int, event: KeyEvent): Boolean {

        if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
            val currentPinIndex = findIndex(view.id)
            if (currentPinIndex > 0 && pinsViews[currentPinIndex].text.isNullOrEmpty()) {
                requestFocus(pinsViews[currentPinIndex - 1])
            }
        }
        return false
    }

    private fun findIndex(id: Int): Int {
        for ((index, pin) in pinsViews.withIndex()) {
            if (pin.id == id) {
                return index
            }
        }
        return -1
    }

    private fun requestFocus(editPin: EditText) {
        editPin.requestFocus()
        editPin.setSelection(editPin.text.length)
    }

    private abstract inner class CustomTextWatcher : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            val text = p0.toString()
            if (text.isNotEmpty()) {
                var other: String? = null
                if (text.length > 1) {
                    other = text.substring(1)
                } else if (text.length == 1) {
                    other = ""
                }

                onTextChanged(text[0], other)
            }
            val pinsCode = getPinsCode()
            if (pinsCode.length == pinsViews.size) {
                onPinsCompleteListener.onPinsCompleted(pinsCode)
            } else {
                onPinsCompleteListener.onPinsCodeChanged(pinsCode)
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }

        abstract fun onTextChanged(char: Char, trailingText: String?)
    }

    interface OnPinsCompleteListener {
        fun onPinsCompleted(code: String)

        fun onPinsCodeChanged(code: String)
    }
}