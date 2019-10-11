package com.ioasys.empresas.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioasys.empresas.data.EmpresasRepository
import com.ioasys.empresas.model.Enterprise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(
    val headersMap: Map<String, String>
) : ViewModel() {

    var name: MutableLiveData<String> = MutableLiveData<String>(null)
    var enterprises: MutableLiveData<List<Enterprise>> = MutableLiveData<List<Enterprise>>(null)

    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    fun getEnterprises(): Unit {
        scope.launch {
            try {
                enterprises.postValue(
                    EmpresasRepository.getInstance()
                        .getEnterprise(map = headersMap, name = name.value, type = null)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}