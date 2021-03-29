package app.moviebase.androidx.widget.recyclerview.adapter.list

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager

interface GlideViewLoader<V> {

    val requests: RequestManager

    fun load(model: V?, holder: RecyclerView.ViewHolder): RequestBuilder<Drawable>

    fun preload(model: V?, holder: RecyclerView.ViewHolder?): RequestBuilder<Drawable>

    fun clearGlide(imageView: ImageView)

    fun getTag(model: V?): String? = null

}
