package com.fearaujo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contractor(
        val title: String?,
        val id: Int?,
        val photo: String?,
        val client: Client?,
        @SerializedName("job_category") val jobCategory: JobCategory?,
        @SerializedName("max_possible_earnings_hour") val maxEarningHour: Float?,
        val shifts: List<Shift>?
) : Parcelable

@Parcelize
data class Client(
        val photos: List<Photo>?,
        val description: String?
) : Parcelable

@Parcelize
data class Photo(
        val formats: List<Format>?
): Parcelable

@Parcelize
data class Format(
        @SerializedName("cdn_url") val cdnUrl: String?
): Parcelable

@Parcelize
data class JobCategory(
        val description: String?,
        @SerializedName("icon_path") val iconPath: String?
): Parcelable

@Parcelize
data class Shift(
        @SerializedName("earnings_per_hour") val earningsPerHour: Float?,
        @SerializedName("start_date") val startDate: String?,
        @SerializedName("start_time") val startTime: String?,
        @SerializedName("end_time") val endTime: String?
): Parcelable