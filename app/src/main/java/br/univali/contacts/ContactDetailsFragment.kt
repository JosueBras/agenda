package br.univali.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import br.univali.contacts.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private lateinit var viewModel: ContactDetailViewModel
    private var contact: Contact? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactDetailViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        contact = requireArguments().getSerializable("contact") as Contact?
        if (contact != null) {
            binding.name.editText!!.setText(contact!!.name)
            binding.mainPhone.editText.setText(contact!!.phone)
        } else {
            binding.buttonDelete.visibility = View.GONE
        }
        binding.buttonSave.setOnClickListener { v ->
            if (!validate()) return@setOnClickListener
            if (contact == null) {
                contact = Contact()
                contact!!.name = binding.name.editText!!.text.toString()
                contact!!.phone = binding.mainPhone.editText.text.toString()
                viewModel.add(context!!, contact!!)
            } else {
                contact!!.name = binding.name.editText!!.text.toString()
                contact!!.phone = binding.mainPhone.editText.text.toString()
                viewModel.update(context!!, contact!!)
            }
            findNavController(v).navigate(R.id.action_from_contact_details_to_contact_list)
        }
        binding.buttonDelete.setOnClickListener { v ->
            viewModel.delete(context!!, contact!!)
            findNavController(v).navigate(R.id.action_from_contact_details_to_contact_list)
        }
        binding.buttonAddPhone.setOnClickListener {
            binding.phones.addView(PhoneEditText(context!!))
        }
        return binding.root
    }

    private fun validate(): Boolean {
        var error: String? = null
        if (binding.name.editText!!.text.toString().isEmpty()) {
            error = "Nome está vazio!"
        } else if (binding.mainPhone.editText.text.toString().isEmpty()) {
            error = "Telefone está vazio!"
        }
        if (error != null) {
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
        return error == null
    }
}
