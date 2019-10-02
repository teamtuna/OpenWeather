package com.karrel.openweather.extension

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun View.alphaAnim() {
    this.alpha = 0f
    this.animate().alpha(1f).start()
}

fun ImageView.loadImage(url: String, onSuccess: () -> Unit = {}, onFailed: () -> Unit = {}) {
    Glide.with(this).load(url).listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            onFailed()
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onSuccess()
            return false
        }

    }).into(this)
}