package org.lico.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget

object ImageLoad {

    fun loadImage(context: Context, url: String?, imageView: ImageView) {
        Glide.with(context).load(url).into(imageView)

    }

    fun getBitmap(context: Context, url: String?): Bitmap? {
        var bitmap: Bitmap? = null
        val bitmaps: FutureTarget<Bitmap> = Glide.with(context)
            .asBitmap()
            .load(url)
            .submit()

        try {
            bitmap = bitmaps.get()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return bitmap

        }
    }
}