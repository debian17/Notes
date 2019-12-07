package github.debian17.notes.base.di

import androidx.lifecycle.ViewModelProviders
import github.debian17.domain.injector.NotesInjector
import github.debian17.notes.base.mvvm.BaseFragment
import github.debian17.notes.ui.notes.NotesViewModel

object ViewModelInjector {

    fun provideNotesViewModel(fragment: BaseFragment): NotesViewModel {
        val getNotes = NotesInjector.provideGetNotes()
        val addNote = NotesInjector.provideAddNote()
        val viewModelFactory = NotesViewModel.NotesViewModelFactory(getNotes, addNote)
        return ViewModelProviders.of(fragment, viewModelFactory).get(NotesViewModel::class.java)
    }

}