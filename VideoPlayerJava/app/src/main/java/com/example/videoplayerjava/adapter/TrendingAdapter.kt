package com.example.videoplayerjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoplayerjava.R
import com.example.videoplayerjava.model.YoutubeVideo
import com.example.videoplayerjava.utils.DurationUtils
import com.example.videoplayerjava.utils.TimeUtils

class TrendingAdapter(
        private val youtubeVideos: MutableList<YoutubeVideo>,
        private val context: Context,
        recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    //    boolean isLoading;
    //    Integer visibleThreshold = 0;
    var lastVisibleItem: Int? = null
    var totalItemCount: Int? = null

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayoutManager!!.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
            }
        })
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvVideoTitle: TextView
        var tvChannelName: TextView
        var ivThumnailVideo: ImageView
        var tvPublishTime: TextView
        var tvTimeDuration: TextView
        var tvViewCount: TextView

        init {
            tvVideoTitle = itemView.findViewById(R.id.tv_video_title)
            tvChannelName = itemView.findViewById(R.id.tv_channel_name)
            ivThumnailVideo = itemView.findViewById(R.id.iv_thumbnail_video)
            tvPublishTime = itemView.findViewById(R.id.tv_publish_time)
            tvTimeDuration = itemView.findViewById(R.id.tv_time_duration)
            tvViewCount = itemView.findViewById(R.id.tv_view_count)
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progress_bar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_youtube, parent, false)
            return VideoViewHolder(itemView)
        } else if (viewType == VIEW_TYPE_LOADING) {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loadinng, parent, false)
            return LoadingViewHolder(itemView)
        }
        return VideoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        VIEW_TYPE_ITEM,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VideoViewHolder) {
            val ytv = youtubeVideos[position]
            val getTvChannelName = ytv.snippet?.channelTitle
            val getTvVideoTitle = ytv.snippet?.title
            val getTvPublishTime = TimeUtils.convertDate(ytv.snippet?.publishedTime) + " days ago"
            val getTvTimeDuration = DurationUtils.convertTime(ytv.contentDetails?.duration)

            val getTvViewCount = ytv.statistics?.withSuffix(ytv.statistics?.viewCount) + " views"
            var getThumbUrl: String? = ytv.snippet?.thumbnails?.standard?.url
            holder.tvChannelName.text = getTvChannelName
            holder.tvVideoTitle.text = getTvVideoTitle
            holder.tvPublishTime.text = getTvPublishTime
            holder.tvTimeDuration.text = getTvTimeDuration
            holder.tvViewCount.text = getTvViewCount
            Glide.with(context).load(getThumbUrl)
                    .into(holder.ivThumnailVideo)
        } else if (holder is LoadingViewHolder) {
            holder.progressBar.isIndeterminate = true
        }
    }

    override fun getItemCount(): Int {
        return youtubeVideos.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (youtubeVideos[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    fun hideFooter() {
        if (youtubeVideos.size > 0) {
            youtubeVideos.removeAt(youtubeVideos.size - 1)
            notifyDataSetChanged()
        }
    }


}