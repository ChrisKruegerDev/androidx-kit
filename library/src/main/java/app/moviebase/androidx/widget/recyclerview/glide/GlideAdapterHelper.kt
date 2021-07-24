package app.moviebase.androidx.widget.recyclerview.glide

import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.viewholder.ImageViewHolder

object GlideAdapterHelper {

    fun updateImageView(glideConfig: GlideConfig<*>, holder: RecyclerView.ViewHolder?) {
        if (holder is ImageViewHolder)
            glideConfig.preloadProvider.setView(holder.imageView)
    }

    fun <T> bindImageView(glideConfig: GlideConfig<T>, value: T?, holder: RecyclerView.ViewHolder?) {
        if (holder is ImageViewHolder) {
            val imageView = holder.imageView

            val tag = imageView.tag
            val newTag = glideConfig.loader?.getTag(value)
            // don't load it again if it has the same tag
            if (tag != null && newTag == tag) return

            val requestBuilder = glideConfig.loader?.load(value, holder)
            requestBuilder?.into(imageView)?.waitForLayout()
            imageView.tag = newTag
        }
    }

    fun clearImageView(glideConfig: GlideConfig<*>, holder: RecyclerView.ViewHolder?) {
        if (holder is ImageViewHolder) {
            val imageView = holder.imageView
            glideConfig.loader?.clearGlide(imageView)
            imageView.tag = null
        }
    }

}
