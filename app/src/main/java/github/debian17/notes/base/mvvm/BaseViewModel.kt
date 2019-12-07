package github.debian17.notes.base.mvvm

import androidx.lifecycle.*

abstract class BaseViewModel : ViewModel() {

    protected val isLoading = MutableLiveData<Boolean>()
    protected val isError = MutableLiveData<Throwable>()

    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isLoading.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<Throwable>) {
        isError.observe(owner, observer)
    }

}