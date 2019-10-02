package github.debian17.notes.base.extension

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subscribeOnIO() = subscribeOn(Schedulers.io())

fun <T> Single<T>.observeOnUI() = observeOn(AndroidSchedulers.mainThread())