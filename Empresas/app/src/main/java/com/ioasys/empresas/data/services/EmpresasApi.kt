package com.ioasys.empresas.data.services

import com.ioasys.empresas.model.Enterprises
import com.ioasys.empresas.model.User
import com.ioasys.empresas.model.UserBody
import com.ioasys.empresas.model.getEnterprise
import retrofit2.Response
import retrofit2.http.*

interface EmpresasApi {
    @POST("users/auth/sign_in")
    suspend fun getUser(@Body userBody: UserBody): Response<User>

    @GET("enterprises/{number}")
    suspend fun getEnterprise(
        @HeaderMap map: Map<String, String>,
        @Path("number") number: Int
    ): getEnterprise

    @GET("enterprises")
   suspend fun getEnterprises(
        @HeaderMap map: Map<String, String>,
        @Query("enterprise_types") type: Int?,
        @Query("name") name: String?
    ): Enterprises
}