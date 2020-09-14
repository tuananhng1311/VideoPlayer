package com.example.videoplayerjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoplayerjava.R
import com.example.videoplayerjava.model.searchModel.SearchVideo
import kotlinx.android.synthetic.main.item_result_videos.view.*
import com.example.videoplayerjava.utils.TimeUtils
import org.w3c.dom.Text

class SearchingAdapter(
        private val resultVideos: MutableList<SearchVideo>,
        private val context: Context,
        recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lastVisibleItem: Int? = null
    var totalItemCount: Int? = null

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayoutManager!!.itemCount
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()
            }
        })
    }

    class ResultVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvVideoTitleR: TextView
        var tvChannelNameR: TextView
        var tvPublishTimeR: TextView
        var ivThumbnailVideoR: ImageView

        init {
            tvVideoTitleR = itemView.tv_video_title_result
            tvChannelNameR = itemView.tv_channel_name_result
            tvPublishTimeR = itemView.tv_publish_time_result
            ivThumbnailVideoR = itemView.iv_thumbnail_video_result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_result_videos, parent, false)
        return ResultVideoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return resultVideos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ResultVideoViewHolder) {
            val resultVid = resultVideos[position]
            val getTvVideoTitleR = resultVid.snippet?.title
            val getTvChannelNameR = resultVid.snippet?.channelTitle
            val getTvPublishTimeR = TimeUtils.convertDate(resultVid.snippet?.publishedTime) + " days ago"
            val getIvThumbnailVideoR: String? = resultVid.snippet?.thumbnails?.high?.url
            holder.tvVideoTitleR.text = getTvVideoTitleR
            holder.tvChannelNameR.text = getTvChannelNameR
            holder.tvPublishTimeR.text = getTvPublishTimeR
            Glide.with(context).load(getIvThumbnailVideoR).into(holder.ivThumbnailVideoR)
        }
    }

    fun hideFooter() {
        if (resultVideos.size > 0) {
            resultVideos.removeAt(resultVideos.size - 1)
            notifyDataSetChanged()
        }
    }
}