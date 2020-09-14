package com.example.videoplayerjava.model

import com.google.gson.annotations.SerializedName

class DataResponse {
    @SerializedName("items")
    var videos: MutableList<YoutubeVideo>? = null

    @SerializedName("nextPageToken")
    var nextPage: String? = null

}