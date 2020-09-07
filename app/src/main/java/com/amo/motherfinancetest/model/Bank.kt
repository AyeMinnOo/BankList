package com.amo.motherfinancetest.model

data class Bank(
    val id: Int,
    val name: String,
    val type: String,
    val logoUrl: String
)

data class Banks(val data : List<Bank>)