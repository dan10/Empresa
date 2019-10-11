package com.ioasys.empresas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivityViewModelFactory(
    private val headersMap: Map<String, String>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(headersMap) as T
    }
}