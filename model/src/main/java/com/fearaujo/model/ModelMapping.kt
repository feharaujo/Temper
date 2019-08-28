package com.fearaujo.model

import com.google.gson.annotations.SerializedName

data class Contractor(
        val title: String?,
        val id: Int?,
        val photo: String?,
        val client: Client?,
        @SerializedName("job_category") val jobCategory: JobCategory?
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