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
}

class TrendingController(val _mActivity: Activity, var trendingCallback: ITrending) {
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
}