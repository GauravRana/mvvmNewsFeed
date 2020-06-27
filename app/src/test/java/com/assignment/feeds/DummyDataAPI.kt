package com.assignment.feeds

import com.assignment.feeds.data.remote.api.DataAPI
import com.assignment.feeds.data.remote.response.RepositoriesResponse
import io.reactivex.Single


class DummyDataAPI : DataAPI {

    override fun search(): Single<RepositoriesResponse> {
        return Single.just(null)
    }


}