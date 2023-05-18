package br.univali.contacts

import java.util.*

data class PhoneDTO(
    val id: UUID? = null,
    val number: String,
    val type: PhoneType,
)