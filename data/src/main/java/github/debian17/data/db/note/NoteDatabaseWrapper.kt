package github.debian17.data.db.note

import github.debian17.data.db.model.NoteModel
import io.reactivex.Flowable

class NoteDatabaseWrapper(database: RoomNoteDatabase) : NoteDatabase {

    private val noteDao = database.getNoteDao()

    override fun insert(note: NoteModel) {
        noteDao.insert(note)
    }

    override fun update(note: NoteModel) {
        noteDao.update(note)
    }

    override fun delete(note: NoteModel) {
        noteDao.delete(note)
    }

    override fun getAll(): Flowable<List<NoteModel>> {
        return noteDao.getAll()
    }

}