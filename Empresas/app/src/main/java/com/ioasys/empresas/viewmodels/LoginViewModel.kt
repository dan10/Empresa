package com.ioasys.empresas.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioasys.empresas.data.EmpresasRepository
import com.ioasys.empresas.model.User
import com.ioasys.empresas.model.UserBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Headers
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel() {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var map: MutableLiveData<Map<String, String>> = MutableLiveData(mapOf())

    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    var email: String = ""
    var password: String = ""

    fun attemptAuthencantion() {
        var responseUser: Response<User>

        scope.launch {
            try {
                responseUser = EmpresasRepository.getInstance()
                    .getAuthentication(UserBody(email, password))

                if (responseUser.isSuccessful) {
                    val headers: Headers = responseUser.headers()
                    val accessToken = headers.get("access-token")
                    val client = headers.get("client")
                    val uid = headers.get("uid")
                    map.postValue(mapOf(
                        "access-token" to "$accessToken",
                        "client" to "$client",
                        "uid" to "$uid"
                    ))
                } else {
                    isLoading.postValue(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading.postValue(false)
            }
        }
    }
}