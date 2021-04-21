package com.droidtechlab.shaaditestui.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.droidtechlab.shaaditestui.data.models.ShaadiMatchesListResponse
import com.droidtechlab.shaaditestui.network.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class DataSourceRepo(private val context: Context, private val retrofit: Retrofit) {

    private val compositeDisposable = CompositeDisposable()

    fun getMatchList(): MutableLiveData<Result<ShaadiMatchesListResponse>> {
        val liveData: MutableLiveData<Result<ShaadiMatchesListResponse>> = MutableLiveData()

        compositeDisposable.add(
            retrofit.create(APIEndPoints::class.java).getMatchingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    if (it.results.isNullOrEmpty()) {
                        liveData.postValue(Error(null))
                    } else {
                        liveData.postValue(Success(it))
                    }
                }, { t: Throwable? ->
                    liveData.postValue(
                        Failure(
                            t ?: Throwable("returnMatchesList: Exception is null")
                        )
                    )

                })
        )
        return liveData
    }

    fun destroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}