package ro.ganduraci.rappslib.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    fun loadCircularImageFromResource(imageView: ImageView, resId: Int) {
        Glide.with(imageView.context)
            .load(resId)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    fun loadCircularImageFromUrl(imageView: ImageView, url: String, placeholderResId: Int) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(placeholderResId)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

}