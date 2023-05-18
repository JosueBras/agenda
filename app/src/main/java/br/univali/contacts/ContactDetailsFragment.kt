package br.univali.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import br.univali.contacts.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private lateinit var viewModel: ContactDetailViewModel
    private var data: ContactWithPhoneNumber? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactDetailViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        binding.mainPhone.viewModel = viewModel
        data = requireArguments().getSerializable("contact") as ContactWithPhoneNumber?
        fillForm()
        binding.buttonSave.setOnClickListener { v ->
            viewModel.name = binding.name.editText!!.text.toString()
            viewModel.add(context!!)
            findNavController(v).navigate(R.id.action_from_contact_details_to_contact_list)
        }
        binding.buttonAddPhone.setOnClickListener {
            binding.phones.addView(PhoneEditText(viewModel, context!!))
        }
        return binding.root
    }

    private fun fillForm() {
        if (data != null) {
            binding.name.editText!!.setText(data!!.contact.name)
            binding.mainPhone.editText.setText(data!!.phones.firstOrNull()?.number)
            binding.mainPhone.setPhoneType(PhoneType.values()[data!!.phones.firstOrNull()?.type ?: 0])
            data!!.phones.forEach { phone ->
                if (data!!.phones.indexOf(phone) > 0) {
                    binding.phones.addView(
                        PhoneEditText(viewModel, context!!).apply {
                            editText.setText(phone.number)
                        }
                    )
                }
            }
        } else {
            binding.buttonDelete.visibility = View.GONE
        }
    }
}
