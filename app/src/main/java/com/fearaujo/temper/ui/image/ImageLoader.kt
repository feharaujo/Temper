package com.fearaujo.temper.ui.image

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.fearaujo.temper.R

class ImageLoader(private val requestManager: RequestManager) {

    fun loadImage(url: String, imageView: ImageView) {
        requestManager.load(url)
            .error(R.drawable.temper_icon)
            .centerCrop()
            .into(imageView)
    }

}