package com.ioasys.empresas.data

import android.util.Log
import com.ioasys.empresas.data.services.EmpresasApi
import com.ioasys.empresas.model.Enterprise
import com.ioasys.empresas.model.User
import com.ioasys.empresas.model.UserBody
import kotlinx.coroutines.Deferred
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EmpresasRepository {

    private val baseUrl = "https://empresas.ioasys.com.br/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: EmpresasRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: EmpresasRepository().also { instance = it }
            }
    }

    private fun userService(): EmpresasApi = retrofit.create(EmpresasApi::class.java)

    suspend fun getAuthentication(userBody: UserBody): Response<User> = userService().getUser(userBody)

    suspend fun getEnterprise(map: Map<String, String>, type: Int?, name: String?): List<Enterprise> =
        userService().getEnterprises(map = map, type = type, name = name).enterprises
}