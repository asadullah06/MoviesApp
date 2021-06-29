package com.app.entertainment.movies.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class CommonMethods {
    companion object {
        @JvmStatic
        fun renderImageInView(imageSource: String, imageView: ImageView) {
            Picasso.get().load(IMAGE_BASE_URL + imageSource)
                .into(imageView)
        }
    }
}