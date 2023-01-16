package com.project.wordlearner.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.project.wordlearner.R

object ImageUtil {

    fun loadOfflineImage(imageView: ImageView, image: Int) {
        Glide.with(imageView.context).load(image).placeholder(R.mipmap.ic_launcher).into(imageView)
    }

    fun loadFlagImage(imageView: ImageView, image: String?) {
        Glide.with(imageView.context).load(image).placeholder(R.mipmap.ic_launcher).into(imageView)
    }
    fun loadImage(imageView: ImageView, imageLink: String?) {
        Glide.with(imageView.context)
            .load(imageLink)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .dontTransform()
            .into(imageView)
    }

    fun loadCategoriesImage(imageView: ImageView, imageLink: String?) {
        Glide.with(imageView.context)
            .load(imageLink)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .dontTransform()
            .into(imageView)
    }


}





