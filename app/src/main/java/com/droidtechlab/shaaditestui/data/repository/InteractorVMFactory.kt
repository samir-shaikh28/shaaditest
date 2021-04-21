package com.droidtechlab.shaaditestui.data.repository

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



class InteractorVMFactory internal constructor(private val dataSourceRepo: DataSourceRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Interactor(dataSourceRepo) as T
    }
}