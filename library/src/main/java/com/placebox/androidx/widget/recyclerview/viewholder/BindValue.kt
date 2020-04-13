package com.placebox.androidx.widget.recyclerview.viewholder

interface BindValue<in T> {
    fun bind(value: T?)
}
