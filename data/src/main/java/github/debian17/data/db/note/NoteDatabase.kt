package github.debian17.data.db.note

import github.debian17.data.db.model.NoteModel

interface NoteDatabase {

    suspend fun insert(note: NoteModel)

    fun update(note: NoteModel)

    fun delete(note: NoteModel)

    suspend fun getAll(): List<NoteModel>

}