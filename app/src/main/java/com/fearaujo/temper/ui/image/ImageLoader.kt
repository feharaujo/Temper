package com.fearaujo.temper.ui.image

import android.widget.ImageView
import com.bumptech.glide.RequestManager

class ImageLoader(private val requestManager: RequestManager) {

    fun loadImage(url: String, imageView: ImageView) {
        requestManager.load(url)
                .centerCrop()
                .into(imageView)
    }

}