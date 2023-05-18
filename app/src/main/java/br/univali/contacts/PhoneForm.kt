package br.univali.contacts

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*

class PhoneForm(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {
    val editText get() = findViewById<EditText>(R.id.edit_text)!!
    val delete get() = findViewById<View>(R.id.delete_phone_number)!!
    private val spinner get() = findViewById<Spinner>(R.id.phone_type)
    lateinit var viewModel: ContactDetailViewModel
    lateinit var type: PhoneType
    private lateinit var phoneDTO: PhoneDTO

    constructor(viewModel: ContactDetailViewModel, context: Context) : this(context) {
        this.viewModel = viewModel
    }

    init {
        inflate(context, R.layout.edit_text_phone, this)
        editText.imeOptions = EditorInfo.IME_ACTION_DONE
        setPhoneTypeSpinner()
        delete.setOnClickListener {
            if (::phoneDTO.isInitialized) {
                viewModel.deletePhone(context, phoneDTO)
            }
            visibility = GONE
        }
    }

    fun setPhoneDTO(dto: PhoneDTO) {
        phoneDTO = dto
        spinner.setSelection(dto.type.ordinal)
    }

    private fun setPhoneTypeSpinner() {
        val adapter = ArrayAdapter.createFromResource(context, R.array.phone_type, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                type = PhoneType.values()[pos]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun setPhoneType(type: PhoneType) {
        this.type = type
        spinner.setSelection(type.ordinal)
    }
}