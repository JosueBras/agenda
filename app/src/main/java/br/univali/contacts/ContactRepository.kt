package br.univali.contacts

import android.content.Context

class ContactRepository {
    fun findAll(context: Context): List<ContactWithPhoneNumber> {
        return ContactDatabase.getInstance(context).contactDao().findAll()
    }

    fun create(context: Context, name: String, phones: List<String>) {
        val contact = Contact(name)
        ContactDatabase.getInstance(context).contactDao().add(contact)
        phones.forEach { phone ->
            val number = PhoneNumber(contact, phone)
            ContactDatabase.getInstance(context).contactDao().addPhoneNumber(number)
        }
    }

    fun update(context: Context, id: Int, name: String, phones: List<String>) {}

    fun deleteById(context: Context, id: Int) {}
}