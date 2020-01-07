package github.debian17.data.source

import github.debian17.data.db.model.NoteModel
import org.threeten.bp.LocalDateTime

interface NoteDataSource {

    suspend fun getNotes(): List<NoteModel>

    suspend fun addNote(
        title: String,
        content: String,
        dateOfCreation: LocalDateTime,
        isDeleted: Boolean,
        images: List<String>?,
        records: List<String>?
    )

    suspend fun deleteNote(id: Int)

}