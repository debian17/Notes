package github.debian17.notes.di.module

import dagger.Module
import dagger.Provides
import github.debian17.data.db.model.NoteModel
import github.debian17.data.source.NoteDataSource
import github.debian17.notes.mapper.Mapper
import github.debian17.notes.model.Note
import github.debian17.notes.ui.notes.NotesViewModel

@Module(includes = [RepositoryModule::class, MapperModule::class])
class ViewModelFactoryModule {

    @Provides
    fun provideNotesViewModelFactory(
        noteDataSource: NoteDataSource,
        mapper: Mapper<NoteModel, Note>
    ): NotesViewModel.NotesViewModelFactory {
        return NotesViewModel.NotesViewModelFactory(noteDataSource, mapper)
    }

}