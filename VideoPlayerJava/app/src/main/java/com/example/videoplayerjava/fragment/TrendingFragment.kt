package com.example.videoplayerjava.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayerjava.R
import com.example.videoplayerjava.adapter.TrendingAdapter
import com.example.videoplayerjava.controller.ITrending
import com.example.videoplayerjava.controller.TrendingController
import com.example.videoplayerjava.model.YoutubeVideo
import kotlinx.android.synthetic.main.fragment_trending.*
import me.yokeyword.fragmentation.SupportFragment
import java.io.IOException
import java.util.*

class TrendingFragment : SupportFragment(), ITrending {
    private var trendingAdapter: TrendingAdapter? = null
    private var trendingControl: TrendingController? = null
    private val trendingVideos: MutableList<YoutubeVideo> = ArrayList()
    private val progressBar: ProgressBar? = null
    private var pageToken = ""

    private val URL = "https://www.googleapis.com/youtube/v3/videos?chart=mostPopular&part=snippet,contentDetails,statistics&regionCode=vn&maxResults=20&key=AIzaSyBTmV7xVBPpJgC4m7jqNnjHqUYAuvelsh8&fbclid=IwAR1pbY2XRfCsgjq3bIuUJ6x_SHJRGm9yfwdkkzI--2Wwqju-pG_2-vnyuco"

    companion object {
        fun newInstance(): TrendingFragment {
            val args = Bundle()
            val fragment = TrendingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_trending, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    @SuppressLint("WrongConstant")
    private fun initView() {
        recy.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trendingAdapter
        }
        trendingAdapter = TrendingAdapter(trendingVideos, _mActivity, recy)
        recy.adapter = trendingAdapter
        if (progressBar != null) {
            progressBar.visibility = View.GONE
        }
        trendingControl = TrendingController(_mActivity, this)
        trendingControl!!.loadData(URL)
        refresh_layout.setOnRefreshListener {
            trendingVideos.clear()
            trendingControl!!.loadData(URL)
            refresh_layout.isRefreshing = false
            trendingAdapter?.notifyDataSetChanged()
            Toast.makeText(refresh_layout.getContext(), "Refreshing", 0).show()
        }
    }


    override fun onLoadedData(resultVideos: MutableList<YoutubeVideo>) {
        Log.e("Videos", "Loaded")
        trendingVideos.addAll(resultVideos)
        trendingAdapter!!.hideFooter()
        trendingAdapter!!.notifyDataSetChanged()
    }
}