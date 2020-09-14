package com.example.videoplayerjava.model

class Statistics(var viewCount: String) {

    fun getViewCount(viewCount: String) {
        this.viewCount = viewCount
    }

    fun withSuffix(viewCount: String?): String {
        if (java.lang.Long.valueOf(viewCount!!) < 1000) return "" + java.lang.Long.valueOf(viewCount)
        val exp = (Math.log(java.lang.Long.valueOf(viewCount).toDouble()) / Math.log(1000.0)).toInt()
        return String.format("%.1f %c",
                java.lang.Long.valueOf(viewCount) / Math.pow(1000.0, exp.toDouble()),
                "kMGTPE"[exp - 1])
    }

}