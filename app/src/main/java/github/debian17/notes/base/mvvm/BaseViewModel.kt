package github.debian17.notes.base.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val subscriptions = CompositeDisposable()

    protected val isLoading = MutableLiveData<Boolean>()
    protected val isError = MutableLiveData<Throwable>()

    protected fun unsubscribeOnClear(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isLoading.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<Throwable>) {
        isError.observe(owner, observer)
    }

    @CallSuper
    override fun onCleared() {
        subscriptions.clear()
    }

}