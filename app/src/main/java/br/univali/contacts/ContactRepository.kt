package br.univali.contacts

import android.content.Context

class ContactRepository {
    private fun getDao(context: Context) = ContactDatabase.getInstance(context).contactDao()

    fun findAll(context: Context): List<ContactWithPhoneNumber> {
        return getDao(context).findAll()
    }

    fun create(context: Context, name: String, phones: List<PhoneDTO>) {
        val contact = Contact(name)
        getDao(context).add(contact)
        phones.forEach { phone ->
            val number = PhoneNumber(contact, phone.number, phone.type)
            getDao(context).addPhoneNumber(number)
        }
    }

    fun update(context: Context, id: String, name: String, phones: List<PhoneDTO>) {
        val result = getDao(context).findById(id)
        result.phones.forEach { getDao(context).deletePhoneNumber(it) }
        phones.forEach {
            getDao(context).addPhoneNumber(
                PhoneNumber(
                    result.contact,
                    it.number,
                    it.type,
                ),
            )
        }
        result.contact.name = name
        getDao(context).update(result.contact)
    }

    fun deletePhone(context: Context, phoneDTO: PhoneDTO) {
        val number = getDao(context).findPhoneNumberById(phoneDTO.id!!)
        getDao(context).deletePhoneNumber(number)
    }

    fun deleteById(context: Context, id: String) {
        val result = getDao(context).findById(id)
        getDao(context).delete(result.contact)
    }
}