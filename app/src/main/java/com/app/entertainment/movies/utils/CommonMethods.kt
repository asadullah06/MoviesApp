package com.app.entertainment.movies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import com.squareup.picasso.Picasso


object CommonMethods {

    @JvmStatic
    fun renderImageInView(imageSource: String, imageView: ImageView) {
        Picasso.get().load(IMAGE_BASE_URL + imageSource)
            .into(imageView)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            //It will check for both wifi and cellular network
            return networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
        return false
    }
}