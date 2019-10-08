package github.debian17.data.repository

import github.debian17.data.db.model.NoteModel
import github.debian17.data.db.note.NoteDatabase
import github.debian17.data.source.NoteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class NoteRepository(private val database: NoteDatabase) : NoteDataSource {

    override fun getNotes(): Flowable<List<NoteModel>> {
        return database.getAll()
    }

    override fun addNote(note: NoteModel): Completable {
        return Completable.fromAction {
            database.insert(note)
        }
    }

}