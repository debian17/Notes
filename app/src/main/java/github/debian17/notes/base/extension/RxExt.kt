package github.debian17.notes.base.extension

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.subscribeOnIO() = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.observeOnUI() = observeOn(AndroidSchedulers.mainThread())

