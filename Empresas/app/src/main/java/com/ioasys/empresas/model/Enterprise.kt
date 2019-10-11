package com.ioasys.empresas.model

data class Enterprise(
    val id: Int,
    val email_enterprise: String,
    val facebook: String,
    val twitter: String,
    val linkedin: String,
    val phone: String,
    val own_enterprise: Boolean,
    val enterprise_name: String,
    val photo: String,
    val description: String,
    val city: String,
    val country: String,
    val value: Int,
    val share_price: Double,
    val enterprise_type: EnterpriseType
)
