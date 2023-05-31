package com.example.musicchallenge.domain.utils.common

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.musicchallenge.R

internal suspend fun Uri.asArtworkBitmap(context: Context): Bitmap? {
    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(this)
        .placeholder(R.drawable.music_cover)
        .error(R.drawable.music_cover)
        .build()

    val drawable = loader.execute(request).drawable
    return drawable?.toBitmap()
}
