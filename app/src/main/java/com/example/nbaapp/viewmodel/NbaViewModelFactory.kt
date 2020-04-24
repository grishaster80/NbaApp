package com.example.nbaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NbaViewModelFactory @Inject constructor(
    private val nbaViewModel: NbaViewModel):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NbaViewModel::class.java)) return nbaViewModel as T
        throw IllegalArgumentException("Unknown class name")
    }

}
