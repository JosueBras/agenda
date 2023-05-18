package br.univali.contacts

data class PhoneDTO(
    val id: Int? = null,
    val number: String,
    val type: PhoneType,
)