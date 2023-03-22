package app.moviebase.androidx.type

fun Boolean?.isTrue(): Boolean = this != null && this == true
fun Boolean?.isFalse(): Boolean = this != null && this == false
fun Boolean?.isNullOrFalse(): Boolean = this == null || this == false
