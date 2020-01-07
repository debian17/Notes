package github.debian17.domain.injector

import android.content.Context
import androidx.room.Room
import github.debian17.data.db.model.NoteModel
import github.debian17.data.db.note.NoteDatabase
import github.debian17.data.db.note.NoteDatabaseWrapper
import github.debian17.data.db.note.RoomNoteDatabase
import github.debian17.data.repository.NoteRepository
import github.debian17.data.source.NoteDataSource
import github.debian17.domain.mapper.Mapper
import github.debian17.domain.mapper.NoteMapper
import github.debian17.domain.model.Note
import github.debian17.domain.notes.AddNote
import github.debian17.domain.notes.DeleteNote
import github.debian17.domain.notes.GetNotes

object NotesInjector {

    private lateinit var noteDatabase: NoteDatabase

    fun init(applicationContext: Context, databaseName: String) {
        val roomNoteDatabase = Room.databaseBuilder(
            applicationContext,
            RoomNoteDatabase::class.java,
            databaseName
        ).build()

        noteDatabase = NoteDatabaseWrapper(roomNoteDatabase)
    }

    private fun provideNoteDataSource(): NoteDataSource {
        return NoteRepository(noteDatabase)
    }

    fun provideGetNotes(): GetNotes {
        val noteDataSource: NoteDataSource = provideNoteDataSource()
        val mapper: Mapper<NoteModel, Note> = NoteMapper()
        return GetNotes(noteDataSource, mapper)
    }

    fun provideAddNote(): AddNote {
        val noteDataSource: NoteDataSource = provideNoteDataSource()
        return AddNote(noteDataSource)
    }

    fun provideDeleteNote(): DeleteNote {
        val noteDataSource: NoteDataSource = provideNoteDataSource()
        return DeleteNote(noteDataSource)
    }

}