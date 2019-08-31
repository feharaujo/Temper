package com.fearaujo.model

import com.google.gson.annotations.SerializedName

data class Contractor(
        val title: String?,
        val id: Int?,
        val photo: String?,
        val client: Client?,
        @SerializedName("job_category") val jobCategory: JobCategory?,
        @SerializedName("max_possible_earnings_hour") val maxEarningHour: Float?,
        val shifts: List<Shift>?
)

data class Client(
        val photos: List<Photo>?,
        val description: String?
)

data class Photo(
        val formats: List<Format>?
)

data class Format(
        @SerializedName("cdn_url") val cdnUrl: String?
)

data class JobCategory(
        val description: String?,
        @SerializedName("icon_path") val iconPath: String?
)

data class Shift(
        @SerializedName("start_time") val startTime: String?,
        @SerializedName("end_time") val endTime: String?
)