package com.example.videoplayerjava.model.searchModel

import com.example.videoplayerjava.model.YoutubeVideo
import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("items")
    var resultVideos: MutableList<SearchVideo>? = null

}