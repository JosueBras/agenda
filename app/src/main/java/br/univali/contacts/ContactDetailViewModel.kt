package br.univali.contacts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailViewModel : ViewModel() {
    fun add(context: Context, contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            ContactDatabase.getInstance(context).contactDao().add(contact)
        }
    }

    fun delete(context: Context, contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            ContactDatabase.getInstance(context).contactDao().delete(contact)
        }
    }

    fun update(context: Context, contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            ContactDatabase.getInstance(context).contactDao().update(contact)
        }
    }
}
