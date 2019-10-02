package github.debian17.notes.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.debian17.data.db.model.NoteModel
import github.debian17.data.source.NoteDataSource
import github.debian17.notes.base.extension.observeOnUI
import github.debian17.notes.base.extension.subscribeOnIO
import github.debian17.notes.base.mvvm.BaseViewModel
import github.debian17.notes.mapper.Mapper
import github.debian17.notes.model.Note

class NotesViewModel(
    private val noteDataSource: NoteDataSource,
    private val mapper: Mapper<NoteModel, Note>
) : BaseViewModel() {

    private val notes = MutableLiveData<List<Note>>()

    init {
        isLoading.value = true
        unsubscribeOnClear(noteDataSource.getNotes()
            .subscribeOnIO()
            .map { list ->
                return@map list.map { item -> mapper.map(item) }
            }
            .observeOnUI()
            .subscribe(this::onNotesLoaded, this::onLoadingError))
    }

    private fun onNotesLoaded(notes: List<Note>) {
        isLoading.value = false
        this.notes.value = notes
    }

    private fun onLoadingError(throwable: Throwable) {

    }

    @Suppress("UNCHECKED_CAST")
    class NotesViewModelFactory(
        private val noteDataSource: NoteDataSource,
        private val mapper: Mapper<NoteModel, Note>
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NotesViewModel(noteDataSource, mapper) as T
        }
    }

}
