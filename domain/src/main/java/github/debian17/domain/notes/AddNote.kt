package github.debian17.domain.notes

import github.debian17.data.source.NoteDataSource
import github.debian17.domain.base.CoroutineUseCase
import org.threeten.bp.LocalDateTime

class AddNote(
    private val notesDataSource: NoteDataSource
) : CoroutineUseCase<AddNote.Params, Unit>() {

    override suspend fun execute(params: Params) {
        return notesDataSource.addNote(
            title = params.title,
            content = params.content,
            dateOfCreation = params.dateOfCreation,
            isDeleted = params.isDeleted,
            images = params.images,
            records = params.records
        )
    }

    class Params(
        val title: String,
        val content: String,
        val dateOfCreation: LocalDateTime,
        val isDeleted: Boolean,
        val images: List<String>?,
        val records: List<String>?
    )
}