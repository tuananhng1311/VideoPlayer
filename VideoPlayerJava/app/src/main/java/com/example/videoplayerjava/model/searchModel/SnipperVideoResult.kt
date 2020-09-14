package com.example.videoplayerjava.model.searchModel

import com.google.gson.annotations.SerializedName

class SnipperVideoResult(
        @SerializedName("publishedAt")
        var publishedTime: String?,
        var title: String,
        var thumbnails: ThumbnailResult,
        var channelTitle: String
) {

}
