package github.debian17.notes.ui.notes

import androidx.lifecycle.*
import github.debian17.domain.model.Note
import github.debian17.domain.notes.AddNote
import github.debian17.notes.base.mvvm.BaseViewModel
import github.debian17.domain.notes.GetNotes
import kotlinx.coroutines.*
import org.threeten.bp.LocalDateTime
import java.lang.Exception

class NotesViewModel(
    private val getNotes: GetNotes,
    private val addNote: AddNote
) : BaseViewModel() {

    private val notes = MutableLiveData<List<Note>>()

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val params = GetNotes.Params()
                val loadedNotes = getNotes.execute(params)
                notes.value = loadedNotes
            } catch (e: Exception) {
                isError.value = e
            } finally {
                isLoading.value = false
            }
        }
    }

    fun addNote(
        title: String,
        content: String,
        images: List<String>?,
        records: List<String>?
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val params = AddNote.Params(
                    title = title,
                    content = content,
                    dateOfCreation = LocalDateTime.now(),
                    isDeleted = false,
                    images = images,
                    records = records
                )
                addNote.execute(params)
            } catch (e: Exception) {
                isError.value = e
            } finally {
                isLoading.value = false
            }
        }

        getNotes()

    }

    fun observeNotes(owner: LifecycleOwner, observer: Observer<List<Note>>) {
        notes.observe(owner, observer)
    }

    @Suppress("UNCHECKED_CAST")
    class NotesViewModelFactory(
        private val getNotes: GetNotes,
        private val addNote: AddNote
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NotesViewModel(getNotes, addNote) as T
        }
    }

}
