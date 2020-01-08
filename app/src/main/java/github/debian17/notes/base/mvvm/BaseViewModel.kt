package github.debian17.notes.base.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import github.debian17.domain.base.CancelableTask

abstract class BaseViewModel : ViewModel() {

    protected val isLoading = MutableLiveData<Boolean>()
    protected val isError = MutableLiveData<Throwable>()
    protected val runningTasks = ArrayList<CancelableTask>()

    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isLoading.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<Throwable>) {
        isError.observe(owner, observer)
    }

    @CallSuper
    override fun onCleared() {
        runningTasks.forEach {
            if (!it.isDone()) {
                it.cancel(true)
            }
        }
    }

}