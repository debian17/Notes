package github.debian17.data.repository

import github.debian17.data.db.model.NoteModel
import github.debian17.data.db.note.NoteDatabase
import github.debian17.data.source.NoteDataSource
import org.threeten.bp.LocalDateTime

class NoteRepository(private val database: NoteDatabase) : NoteDataSource {

    override suspend fun getNotes(): List<NoteModel> {
        return database.getAll()
    }

    override suspend fun addNote(
        title: String,
        content: String,
        dateOfCreation: LocalDateTime,
        isDeleted: Boolean,
        images: List<String>?,
        records: List<String>?
    ) {
        val noteModel = NoteModel(
            title = title,
            content = content,
            dateOfCreation = dateOfCreation,
            isDeleted = isDeleted,
            images = images ?: emptyList(),
            records = records ?: emptyList()
        )
        return database.insert(noteModel)
    }

}