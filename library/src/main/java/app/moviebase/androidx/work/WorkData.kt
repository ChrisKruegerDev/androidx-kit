package app.moviebase.androidx.work

import androidx.work.Data

fun buildWorkData(vararg data: Data): Data {
    val dataBuilder = Data.Builder()
    data.forEach { dataBuilder.putAll(it) }
    return dataBuilder.build()
}
