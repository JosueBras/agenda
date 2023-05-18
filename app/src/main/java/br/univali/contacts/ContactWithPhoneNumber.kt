package br.univali.contacts

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class ContactWithPhoneNumber(
    @Embedded
    val contact: Contact,

    @Relation(parentColumn = "id", entityColumn = "contactId")
    val phones: List<PhoneNumber>,
) : Serializable