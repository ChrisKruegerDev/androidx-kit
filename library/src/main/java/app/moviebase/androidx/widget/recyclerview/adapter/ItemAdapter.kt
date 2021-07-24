package app.moviebase.androidx.widget.recyclerview.adapter

interface ItemAdapter<T> {
    val config: ItemAdapterConfig<T>

    fun getItemCount(): Int
    fun getItemBy(position: Int): T?
}
