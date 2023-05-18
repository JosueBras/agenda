package br.univali.contacts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailViewModel : ViewModel() {
    var id = ""
    var name: String = ""
    private val repository = ContactRepository()
    private val phones = mutableListOf<PhoneDTO>()

    fun addPhone(phone: PhoneDTO) {
        phones.add(phone)
    }

    fun deletePhone(context: Context, phone: PhoneDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePhone(context, phone)
        }
    }

    fun add(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.create(context, name, phones)
        }
    }

    fun delete(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteById(context, id)
        }
    }

    fun update(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(context, id, name, phones)
        }
    }
}
