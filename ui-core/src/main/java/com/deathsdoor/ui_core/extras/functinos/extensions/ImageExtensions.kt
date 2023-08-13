package com.deathsdoor.ui_core.extras.functinos.extensions

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.RadioButton
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

object ImageExtensions{
    fun RadioButton.loadImg(imgURL: Uri)
        =  Glide.with(this).load(imgURL)
            .placeholder(com.bumptech.glide.R.drawable.notify_panel_notification_icon_bg) //Default img
            .skipMemoryCache(true) //Caching img if phone offline
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) { buttonDrawable = resource }
            })
}

abstract class ImageLoader{
    companion object{
        @Composable
        fun image(@DrawableRes drawable: Int) = painterResource(id = drawable)
    }
}