package com.example.task1.ext

import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

 fun AppCompatImageView.loadImageSvg(url: String) {
    val image = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadImageSvg.context)) }
        .build()
    val request = ImageRequest.Builder(this.context)
        .data(url)
        .target(this)
        .build()
    image.enqueue(request)
}