package com.example.videoplayerjava.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayerjava.R
import com.example.videoplayerjava.adapter.SearchingAdapter
import com.example.videoplayerjava.adapter.TrendingAdapter
import com.example.videoplayerjava.controller.ISearching
import com.example.videoplayerjava.controller.SearchController
import com.example.videoplayerjava.model.DataResponse
import com.example.videoplayerjava.model.YoutubeVideo
import com.example.videoplayerjava.model.searchModel.SearchVideo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search.*
import me.yokeyword.fragmentation.SupportFragment
import okhttp3.*
import java.io.IOException

class SearchFragment : SupportFragment(), ISearching {
    var searchingAdapter: SearchingAdapter? = null
    var searchControl: SearchController? = null
    val searchingVideos: MutableList<SearchVideo> = ArrayList()
    var keywordSearch = "zoo"
    var URL1 = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q="
    var URL2 = "&type=video&key=AIzaSyBTmV7xVBPpJgC4m7jqNnjHqUYAuvelsh8"
//    var urlSearch = "https://www.googleapis.com/youtube/v3/videos?chart=mostPopular&part=snippet,contentDetails,statistics&regionCode=vn&maxResults=20&key=AIzaSyBTmV7xVBPpJgC4m7jqNnjHqUYAuvelsh8&fbclid=IwAR1pbY2XRfCsgjq3bIuUJ6x_SHJRGm9yfwdkkzI--2Wwqju-pG_2-vnyuco"

    companion object {
        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        recyResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchingAdapter
        }
        searchingAdapter = SearchingAdapter(searchingVideos, _mActivity, recyResult)
        recyResult.adapter = searchingAdapter
//        keywordSearch = key_search.text.toString()
        searchControl = SearchController(_mActivity, this)
        searchControl!!.loadData(URL1 + keywordSearch + URL2)
        Log.e("found", "keyword is :" + keywordSearch)
//        trendingAdapter?.notifyDataSetChanged()
    }

    override fun onLoadedData(searchedVideos: MutableList<SearchVideo>) {
        Log.e("Searching Videos", "Searching Loaded")
        searchingVideos.addAll(searchedVideos)
        searchingAdapter!!.hideFooter()
        searchingAdapter!!.notifyDataSetChanged()

    }

}