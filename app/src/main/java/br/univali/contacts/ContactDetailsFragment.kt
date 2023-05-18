package br.univali.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import br.univali.contacts.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private lateinit var viewModel: ContactDetailViewModel
    private var isEditingMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactDetailViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        binding.mainPhone.viewModel = viewModel
        binding.mainPhone.fragment = this
        fillForm()
        binding.buttonSave.setOnClickListener {
            if (validate()) {
                viewModel.name = binding.name.editText!!.text.toString()
                viewModel.phones.clear()
                viewModel.phones.addAll(getPhones())
                if (isEditingMode) {
                    viewModel.update(context!!)
                } else {
                    viewModel.add(context!!)
                }
                findNavController(it).navigate(R.id.action_from_contact_details_to_contact_list)
            } else {
                Toast.makeText(context!!, R.string.invalid_phone, Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonAddPhone.setOnClickListener {
            binding.phones.addView(PhoneForm(viewModel, context!!))
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.delete(context!!)
            findNavController(it).navigate(R.id.action_from_contact_details_to_contact_list)
        }
        return binding.root
    }

    private fun validate(): Boolean {
        if (getPhones().isEmpty()) return false
        fun isValidPhones(): Boolean {
            for (phone in getPhones()) {
                if (phone.number.isBlank()) {
                    return false
                }
            }
            return true
        }
        return binding.name.editText!!.text.isNotBlank() && isValidPhones()
    }

    fun getPhones(): List<PhoneDTO> {
        val phones = mutableListOf<PhoneDTO>()
        binding.phones.forEach {
            if (it is PhoneForm && it.isVisible) {
                it.fragment = this
                phones.add(
                    PhoneDTO(
                        number = it.editText.text.toString(),
                        type = it.type,
                    ),
                )
            }
        }
        return phones
    }

    private fun fillForm() {
        val data = requireArguments().getSerializable("contact") as ContactWithPhoneNumber?
        if (data != null) {
            isEditingMode = true
            viewModel.id = data.contact.id
            binding.name.editText!!.setText(data.contact.name)
            val mainPhone = data.phones.firstOrNull()
            if (mainPhone != null) {
                binding.mainPhone.editText.setText(data.phones.firstOrNull()?.number)
                binding.mainPhone.setPhoneType(PhoneType.values()[mainPhone.type])
                binding.mainPhone.setPhoneDTO(
                    PhoneDTO(
                        id = mainPhone.id,
                        number = mainPhone.number,
                        type = PhoneType.values()[mainPhone.type],
                    ),
                )
            }
            data.phones.forEach { phone ->
                if (data.phones.indexOf(phone) > 0) {
                    binding.phones.addView(
                        PhoneForm(viewModel, context!!).apply {
                            editText.setText(phone.number)
                        }.apply {
                            setPhoneDTO(
                                PhoneDTO(
                                    id = phone.id,
                                    number = phone.number,
                                    type = PhoneType.values()[phone.type],
                                ),
                            )
                        }
                    )
                }
            }
        } else {
            binding.buttonDelete.visibility = View.GONE
        }
    }
}
