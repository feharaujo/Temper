package com.fearaujo.data.extension

import com.fearaujo.model.Contractor

fun Contractor.getImagePath(): String {
    var path = ""

    this.client?.photos?.getOrNull(0)?.formats?.getOrNull(0)?.cdnUrl?.let {
        path = it
    }

    this.photo?.let {
        path = it
    }

    return path
}