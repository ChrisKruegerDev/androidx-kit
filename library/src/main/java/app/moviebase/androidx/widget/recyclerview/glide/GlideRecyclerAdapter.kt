package app.moviebase.androidx.widget.recyclerview.glide

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase

@Deprecated("use GlideItemAdapter directly")
interface GlideRecyclerAdapter<T : Any> : RecyclerViewAdapterBase<T>, GlideItemAdapter<T> {

    override fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val holder = super.onCreate(parent, viewType)
        GlideAdapterHelper.updateImageView(glideConfig, holder)
        return holder
    }

    override fun onBind(value: T?, holder: RecyclerView.ViewHolder) {
        super.onBind(value, holder)
        GlideAdapterHelper.bindImageView(glideConfig, value, holder)
    }

    override fun onClear(holder: RecyclerView.ViewHolder) {
        super.onClear(holder)
        GlideAdapterHelper.clearImageView(glideConfig, holder)
    }
}
