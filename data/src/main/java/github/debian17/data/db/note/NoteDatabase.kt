package github.debian17.data.db.note

import github.debian17.data.db.model.NoteModel

interface NoteDatabase {

    suspend fun insert(note: NoteModel)

    fun update(note: NoteModel)

    suspend fun delete(id: Int)

    suspend fun getAll(): List<NoteModel>

}