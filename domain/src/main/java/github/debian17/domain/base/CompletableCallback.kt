package github.debian17.domain.base

interface CompletableCallback {

    fun onComplete()

    fun onError(t: Throwable)

}