package br.univali.contacts

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout

class PhoneEditText(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {
    val editText get() = findViewById<EditText>(R.id.edit_text)!!

    init {
        inflate(context, R.layout.edit_text_phone, this)
    }
}