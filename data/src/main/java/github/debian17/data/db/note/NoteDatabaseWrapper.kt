package github.debian17.data.db.note

import github.debian17.data.db.model.NoteModel

class NoteDatabaseWrapper(database: RoomNoteDatabase) : NoteDatabase {

    private val noteDao = database.getNoteDao()

    override suspend fun insert(note: NoteModel) {
        noteDao.insert(note)
    }

    override fun update(note: NoteModel) {
        noteDao.update(note)
    }

    override fun delete(note: NoteModel) {
        noteDao.delete(note)
    }

    override suspend fun getAll(): List<NoteModel> {
        return noteDao.getAll()
    }

}