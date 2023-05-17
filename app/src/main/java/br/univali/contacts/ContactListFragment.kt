package br.univali.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.univali.contacts.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    private val viewModel: ContactListViewModel by viewModels()
    private lateinit var binding: FragmentContactListBinding

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.contacts.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = ContactListAdapter(it)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.findAll(requireContext())
    }
}
