package github.debian17.data.db.note

import github.debian17.data.db.model.NoteModel
import io.reactivex.Single

interface NoteDatabase {

    fun insert(note: NoteModel)

    fun update(note: NoteModel)

    fun delete(note: NoteModel)

    fun getAll(): Single<List<NoteModel>>

}