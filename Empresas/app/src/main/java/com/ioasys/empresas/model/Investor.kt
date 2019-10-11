package com.ioasys.empresas.model

data class Investor(
    val id: Int,
    val investor_name: String,
    val email: String,
    val city: String,
    val country: String,
    val balance: Double,
    val photo: String,
    val portfolio: Portofolio,
    val portfolio_value: Double,
    val first_access: Boolean,
    val super_angel: Boolean

)