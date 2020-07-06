package com.assignment.feeds.data.remote.response

import com.google.gson.annotations.SerializedName
import com.assignment.feeds.data.remote.domain.Rows

data class RepositoriesResponse(
    @SerializedName("title") val title : String,
    @SerializedName("rows") val rows : ArrayList<Rows>
)