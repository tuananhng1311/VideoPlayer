package com.example.videoplayerjava.controller

import android.app.Activity
import android.util.Log
import com.example.videoplayerjava.model.DataResponse
import com.example.videoplayerjava.model.YoutubeVideo
import com.example.videoplayerjava.model.searchModel.SearchResponse
import com.example.videoplayerjava.model.searchModel.SearchVideo
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

interface ISearching {
    fun onLoadedData(searchedVideos: MutableList<SearchVideo>)
}

class SearchController(val _mActivity: Activity, var searchingCallback: ISearching) {
    fun loadData(searchingURL: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(searchingURL)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()
                val dataJson = response.body!!.string()
                val dataResponse = gson.fromJson(dataJson, SearchResponse::class.java)
                var dataResponseVideos = dataResponse.resultVideos
                Log.e("test video searching", dataResponseVideos?.size.toString() + "")
                _mActivity.runOnUiThread {
                    if (dataResponseVideos != null) {
                        searchingCallback.onLoadedData(dataResponseVideos)
                    }
                }
            }
        })
    }
}