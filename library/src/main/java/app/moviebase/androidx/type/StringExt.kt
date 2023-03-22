package app.moviebase.androidx.type


fun String.extractLeadingDigits(): String {
    val builder = StringBuilder(length)
    toCharArray().takeWhile { Character.isDigit(it) }.forEach { builder.append(it) }
    return builder.toString()
}

fun String.wordCount(): Int {
    val trimmed = trim()
    if (trimmed.isEmpty())
        return 0

    return trimmed.split(Regex("\\s+")).size
}

fun String?.toIntOr(value: Int) = if (this == null || this.isBlank()) value else this.toInt()

fun String?.ifNullOrBlank(value: String?): String? = if (this == null || this.isBlank()) value else this
