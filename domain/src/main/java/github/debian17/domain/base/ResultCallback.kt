package github.debian17.domain.base

interface ResultCallback<T> {

    fun onSuccess(result: T)

    fun onError(t: Throwable)

}