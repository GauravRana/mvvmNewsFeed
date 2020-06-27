package com.assignment.feeds.data.remote.api

import com.assignment.feeds.data.remote.response.RepositoriesResponse
import io.reactivex.Single
import retrofit2.http.GET


interface DataAPI {

    @GET("api/json-storage/bin/dccbbdd")
    fun search(): Single<RepositoriesResponse>

}