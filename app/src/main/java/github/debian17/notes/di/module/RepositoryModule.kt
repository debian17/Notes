package github.debian17.notes.di.module

import dagger.Module
import dagger.Provides
import github.debian17.data.db.note.NoteDatabase
import github.debian17.data.repository.NoteRepository
import github.debian17.data.source.NoteDataSource

@Module(includes = [DatabaseModule::class])
class RepositoryModule {

    @Provides
    fun provideNoteRepository(database: NoteDatabase): NoteDataSource {
        return NoteRepository(database)
    }

}