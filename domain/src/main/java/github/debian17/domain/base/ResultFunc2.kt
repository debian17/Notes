package github.debian17.domain.base

interface ResultFunc2<T1, T2> {

    fun onSuccess(t1: T1, t2: T2)

    fun onError(t: Throwable)

}