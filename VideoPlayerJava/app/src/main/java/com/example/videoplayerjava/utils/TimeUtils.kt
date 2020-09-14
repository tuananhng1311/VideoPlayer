package com.example.videoplayerjava.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TimeUtils {
    companion object{
        fun convertDate(publishedTime: String?): String {
            if (publishedTime == null){
                return ""
            }
            val result: String
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH)
            //        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
            result = try {
                val d = formatter.parse(publishedTime)
                formatter.applyPattern("dd-MM-yyyy-HH:mm:ss")
                formatter.format(d)
            } catch (e: ParseException) {
                e.printStackTrace()
                ""
            }
            //        System.out.println(result);
            val now = LocalDateTime.now()
            val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss")
            val timeCurrent = dateTimeFormatter.format(now)
            val date1 = LocalDateTime.parse(result, dateTimeFormatter)
            val date2 = LocalDateTime.parse(timeCurrent, dateTimeFormatter)
            val a = Duration.between(date1, date2).toDays()

//        System.out.println(daysBetween);
//        System.out.println(timeCurrent);
            return a.toString()
        }
    }
}