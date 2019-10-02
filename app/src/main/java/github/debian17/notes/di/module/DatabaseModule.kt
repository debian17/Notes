package github.debian17.notes.di.module

import dagger.Module
import dagger.Provides
import github.debian17.data.db.note.NoteDatabase
import github.debian17.data.db.note.NoteDatabaseWrapper
import github.debian17.data.db.note.RoomNoteDatabase

@Module
class DatabaseModule(private val database: RoomNoteDatabase) {

    @Provides
    fun provideDatabase(): NoteDatabase {
        return NoteDatabaseWrapper(database)
    }

}