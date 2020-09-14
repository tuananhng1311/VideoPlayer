package com.example.videoplayerjava.utils

class DurationUtils {
    companion object {
        fun convertTime(duration: String?): String {
            if (duration == null) {
                return ""
            }
            val time = duration
            var timeAfter1Cut = ""
            var timeAfter2Cut = ""
            val times = time.split("PT".toRegex()).toTypedArray()
            for (t in times) {
                timeAfter1Cut = timeAfter1Cut + t
            }
            val times2 = timeAfter1Cut.split("S".toRegex()).toTypedArray()
            for (t in times2) {
                timeAfter2Cut = timeAfter2Cut + t
            }
            var time1 = timeAfter2Cut.replace("M", ":")
            val finalTime = time1.replace("H", ":")
            return finalTime
        }
    }
}