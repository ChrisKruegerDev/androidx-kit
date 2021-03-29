package app.moviebase.androidx.widget.recyclerview.paging

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState(
    val status: Status,
    val throwable: Throwable? = null
) {

    companion object {

        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)

        fun error(throwable: Throwable?) = NetworkState(Status.FAILED, throwable)

    }

}
