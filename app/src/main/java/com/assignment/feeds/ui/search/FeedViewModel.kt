package com.assignment.feeds.ui.search

import androidx.lifecycle.MutableLiveData
import com.assignment.feeds.core.BaseViewModel
import com.assignment.feeds.data.remote.api.DataAPI
import com.assignment.feeds.data.remote.domain.Rows
import com.assignment.feeds.util.NotNullMutableLiveData


class FeedViewModel(private val api: DataAPI) : BaseViewModel() {
    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<Rows>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Rows>>
        get() = _items

    private val _title: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String>
        get() = _title


    fun refresh() {
        addToDisposable(api.search()
            .doOnSubscribe { _refreshing.value = true }
            .doOnSuccess { _refreshing.value = false }
            .doOnError { _refreshing.value = false }
            .subscribe({
                _items.value = it.rows
                _title.value = it.title
            }, {
                // handle errors
            })
        )
    }

}