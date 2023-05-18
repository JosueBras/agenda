package br.univali.contacts

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout

class PhoneEditText(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {
    val editText get() = findViewById<EditText>(R.id.edit_text)!!
    lateinit var viewModel: ContactDetailViewModel

    constructor(viewModel: ContactDetailViewModel, context: Context) : this(context) {
        this.viewModel = viewModel
    }

    init {
        inflate(context, R.layout.edit_text_phone, this)
        editText.imeOptions = EditorInfo.IME_ACTION_DONE
        editText.setOnEditorActionListener { textView, actionId, event ->
            viewModel.addPhone(textView.text.toString())
            return@setOnEditorActionListener false
        }
    }
}