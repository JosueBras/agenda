package br.univali.contacts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactListViewModel : ViewModel() {
    private val _contacts = MutableLiveData<List<Contact>>(listOf())
    val contacts: LiveData<List<Contact>> = _contacts;

    fun findAll(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val contacts = ContactDatabase.getInstance(context).contactDao().findAll()
            withContext(Dispatchers.Main) {
                _contacts.value = contacts
            }
        }
    }
}
