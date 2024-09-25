package com.example.superheroapp.data.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadCircleImage(url: String) {
    if (url.isEmpty()) {
        // to do
    } else {
        Glide
            .with(this)
            .applyDefaultRequestOptions(RequestOptions().circleCrop())
            .load(url)
            .into(this)
    }
}

fun ImageView.loadImage(url: String) {
    if (url.isEmpty()) {
        // to do
    } else {
        Glide
            .with(this)
            .load(url)
            .into(this)
    }
}