package br.univali.contacts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel : ViewModel() {
    private val repository = ContactRepository()
    private val _contacts = MutableLiveData<List<ContactWithPhoneNumber>>(listOf())
    val contacts: LiveData<List<ContactWithPhoneNumber>> = _contacts;

    fun findAll(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _contacts.postValue(repository.findAll(context))
        }
    }
}
