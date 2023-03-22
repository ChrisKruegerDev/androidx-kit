package app.moviebase.androidx.type

fun Int?.toIntOrZero() = this ?: 0
fun Int?.isNullOrZero() = this == null || this == 0
fun Int?.isNotNullOrZero() = !isNullOrZero()
fun Int.toEmoji(): String = String(Character.toChars(this))
