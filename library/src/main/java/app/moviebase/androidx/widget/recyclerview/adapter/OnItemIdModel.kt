package app.moviebase.androidx.widget.recyclerview.adapter

class OnValueHashCode<T : Any> : OnItemId<T> {
    override fun invoke(p1: T): Long = p1.hashCode().toLong()
}
