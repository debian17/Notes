package github.debian17.notes.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.debian17.domain.model.Note
import github.debian17.notes.base.extension.observeOnUI
import github.debian17.notes.base.extension.subscribeOnIO
import github.debian17.notes.base.mvvm.BaseViewModel
import github.debian17.domain.notes.GetNotes

class NotesViewModel(
    private val getNotes: GetNotes
) : BaseViewModel() {

    private val notes = MutableLiveData<List<Note>>()

    init {
        isLoading.value = true
        val params = GetNotes.Params()
        unsubscribeOnClear(
            getNotes.execute(params)
                .subscribeOnIO()
                .observeOnUI()
                .subscribe(this::onNotesLoaded, this::onLoadingError)
        )
    }

    private fun onNotesLoaded(notes: List<Note>) {
        isLoading.value = false
        this.notes.value = notes
    }

    private fun onLoadingError(throwable: Throwable) {

    }

    @Suppress("UNCHECKED_CAST")
    class NotesViewModelFactory(
        private val getNotes: GetNotes
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NotesViewModel(getNotes) as T
        }
    }

}
