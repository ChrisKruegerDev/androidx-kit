package app.moviebase.androidx.widget.recyclerview.viewholder

interface BindValue<in T> {
    fun bind(value: T?)
}
