package com.droidtechlab.shaaditestui.network

import com.droidtechlab.shaaditestui.data.models.ShaadiMatchesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndPoints {

    // Assigning default value here
    @GET("/api")
    fun getMatchingList(@Query("results") results: Int = 10): Single<ShaadiMatchesListResponse>
}