package github.debian17.notes.di.component

import dagger.Component
import github.debian17.data.source.NoteDataSource
import github.debian17.notes.di.module.RepositoryModule

@Component(modules = [RepositoryModule::class])
interface DataComponent {

    fun provideNoteRepository(): NoteDataSource

}