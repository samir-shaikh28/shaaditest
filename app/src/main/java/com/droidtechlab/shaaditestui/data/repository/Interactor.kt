package com.droidtechlab.shaaditestui.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidtechlab.shaaditestui.data.models.ShaadiMatchesListResponse
import com.droidtechlab.shaaditestui.network.Result

class Interactor(private val repo: DataSourceRepo): ViewModel() {

    fun returnMatchList(): MutableLiveData<Result<ShaadiMatchesListResponse>> {
        return repo.getMatchList()
    }

    override fun onCleared() {
        repo.destroy()
        super.onCleared()
    }

}