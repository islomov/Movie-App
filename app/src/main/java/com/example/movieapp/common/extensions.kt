package com.example.movieapp.common

import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import java.net.UnknownHostException

fun AppCompatImageView.loadGlide(path: Any?) =
    Glide.with(this.context)
        .load(path)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun AppCompatImageView.glideClear() =
    Glide.with(this.context.applicationContext)
        .clear(this)


fun AppCompatImageView.loadGlideRoundedCorner(path: Any?, radius: Int = 5) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(radius.dp))
    Glide.with(this.context)
        .load(path)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOptions)
        .into(this)
}


fun String.getPath(): String {
    return "${BASE_IMAGE_FILE_PATH}${this}"
}

fun ViewGroup.viewForVH(@LayoutRes id: Int): View {
    return LayoutInflater.from(this.context).inflate(id,this,false)
}

fun Throwable.getAppropriateMessage():String{
    when(this) {
        is UnknownHostException -> {
            return "Problem with connection"
        }
        else -> {
            return "Something went wrong, try again later"
        }
    }
}


fun View.visible() {
    if(this.visibility != View.VISIBLE)
        this.visibility = View.VISIBLE
}

fun View.gone() {
    if(this.visibility != View.GONE)
        this.visibility = View.GONE
}

val Int.dp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()

val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

