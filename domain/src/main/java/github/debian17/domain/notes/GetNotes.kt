package github.debian17.domain.notes

import github.debian17.data.db.model.NoteModel
import github.debian17.data.source.NoteDataSource
import github.debian17.domain.base.CoroutineUseCase
import github.debian17.domain.mapper.Mapper
import github.debian17.domain.model.Note

class GetNotes(
    private val notesDataSource: NoteDataSource,
    private val mapper: Mapper<NoteModel, Note>
) : CoroutineUseCase<GetNotes.Params, List<Note>>() {

    override suspend fun execute(params: Params): List<Note> {
        return notesDataSource.getNotes().map { noteModel -> mapper.map(noteModel) }
    }

    class Params
}