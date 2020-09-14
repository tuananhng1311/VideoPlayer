package com.example.videoplayerjava.model

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SnippetYoutube(
        @SerializedName("publishedAt") var publishedTime: String?,
        var title: String,
        var channelTitle: String,
        var thumbnails: ThumbnailYoutube
) {

}