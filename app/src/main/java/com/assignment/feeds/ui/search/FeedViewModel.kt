package com.assignment.feeds.ui.search

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.feeds.core.BaseViewModel
import com.assignment.feeds.data.remote.api.DataAPI
import com.assignment.feeds.data.remote.db.dao.RowDataDao
import com.assignment.feeds.data.remote.db.entity.RowData
import com.assignment.feeds.data.remote.domain.Rows
import com.assignment.feeds.util.NotNullMutableLiveData
import com.assignment.feeds.util.ioThread


class FeedViewModel(private val api: DataAPI, private val dao: RowDataDao) : BaseViewModel() {
    private val refreshScreen: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = refreshScreen

    private val itemValue: NotNullMutableLiveData<List<Rows>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Rows>>
        get() = itemValue

    private val titleHeader: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String>
        get() = titleHeader

    fun refresh() {
        addToDisposable(api.search()
            .doOnSubscribe { refreshScreen.value = true }
            .doOnSuccess { refreshScreen.value = false }
            .doOnError { refreshScreen.value = false }
            .subscribe({
                itemValue.value = it.rows
                titleHeader.value = it.title
            }, {
                // handle errors
            })
        )
    }

    fun getAllPosts(): LiveData<List<RowData>> {
        return dao.findAll()
    }

    fun saveToDB(repository: Rows) = ioThread { dao.insert(RowData.to(repository)) }

    fun deleteDB() = ioThread { dao.delete() }



}