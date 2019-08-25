package com.fearaujo.model

import com.google.gson.annotations.SerializedName

data class Contractor(
    val title: String?,
    val id: Int?,
    val photo: String?,
    val client: Client?
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