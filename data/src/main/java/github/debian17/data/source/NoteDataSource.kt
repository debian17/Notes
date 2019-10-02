package github.debian17.data.source

import github.debian17.data.db.model.NoteModel
import io.reactivex.Completable
import io.reactivex.Single

interface NoteDataSource {

    fun getNotes(): Single<List<NoteModel>>

    fun addNote(note: NoteModel): Completable

}