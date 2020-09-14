package com.example.videoplayerjava.controller

import android.app.Activity
import android.util.Log
import com.example.videoplayerjava.fragment.TrendingFragment
import com.example.videoplayerjava.model.DataResponse
import com.example.videoplayerjava.model.YoutubeVideo
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

interface ITrending {
    fun onLoadedData(resultVideos: MutableList<YoutubeVideo>)

    fun onLoadMore(loadMoreVideos: MutableList<YoutubeVideo>)
}

class TrendingController(val _mActivity: Activity, var trendingCallback: ITrending) {
    private val URL = "https://www.googleapis.com/youtube/v3/videos?chart=mostPopular&part=snippet,contentDetails,statistics&regionCode=vn&maxResults=20&key=AIzaSyBTmV7xVBPpJgC4m7jqNnjHqUYAuvelsh8&fbclid=IwAR1pbY2XRfCsgjq3bIuUJ6x_SHJRGm9yfwdkkzI--2Wwqju-pG_2-vnyuco"

    fun loadData(mURL: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(mURL)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
//                Thread.sleep(1000)
                val gson = Gson()
                val dataJson = response.body!!.string()
                val dataResponse = gson.fromJson(dataJson, DataResponse::class.java)
                var dataResponseVideos = dataResponse.videos
                Log.e("test", dataResponseVideos?.size.toString() + "")
                _mActivity.runOnUiThread {
                    if (dataResponseVideos != null) {
                        trendingCallback.onLoadedData(dataResponseVideos)
                    }
                }
            }
        })
    }

    fun loadMoreData() {
        var pageToken : String? = null
        var loadMoreURL = URL + "&pageToken=" + pageToken
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(loadMoreURL)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()
                val dataJson = response.body!!.string()
                val dataResponse = gson.fromJson(dataJson, DataResponse::class.java)
                pageToken = dataResponse.nextPage
                val dataResponseVideos = dataResponse.videos
                _mActivity.runOnUiThread {
                    if (dataResponseVideos != null) {
                        trendingCallback.onLoadMore(dataResponseVideos)
                    }
                }
            }
        })
    }
}