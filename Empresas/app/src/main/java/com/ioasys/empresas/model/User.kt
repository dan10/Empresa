package com.ioasys.empresas.model

data class User(
    val investor: Investor,
    val enterprise: String,
    val success: Boolean
)