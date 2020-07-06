package com.assignment.feeds.data.remote.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assignment.feeds.data.remote.db.entity.RowData

@Dao
interface RowDataDao {
    @Query("SELECT * FROM RowData")
    fun findAll(): LiveData<List<RowData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rowData: RowData)

    @Query("DELETE From RowData")
    fun delete()
}