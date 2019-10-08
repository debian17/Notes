package github.debian17.data.source

import github.debian17.data.db.model.NoteModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface NoteDataSource {

    fun getNotes(): Flowable<List<NoteModel>>

    fun addNote(note: NoteModel): Completable

}