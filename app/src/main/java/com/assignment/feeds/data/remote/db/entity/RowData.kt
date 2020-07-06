package com.assignment.feeds.data.remote.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.feeds.data.remote.domain.Rows


@Entity(tableName = "RowData")
data class RowData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "imageHref") val imageHref: String?
) {
    companion object {
        fun to(repository: Rows): RowData {
            if(repository.title.isNullOrEmpty()){
                return RowData(
                    title = "",
                    description = repository.description,
                    imageHref = repository.imageHref
                )
            }
            if(repository.description.isNullOrEmpty()){
                return RowData(
                    title = repository.title,
                    description = "" ,
                    imageHref = repository.imageHref
                )
            }
            if(repository.imageHref.isNullOrEmpty()){
                return RowData(
                    title = repository.title,
                    description = repository.description,
                    imageHref = ""
                )

            }else {
                return RowData(
                    title = repository.title,
                    description = repository.description,
                    imageHref = repository.imageHref
                )
            }
        }
    }
}