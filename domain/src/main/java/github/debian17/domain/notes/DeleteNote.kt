package github.debian17.domain.notes

import github.debian17.data.source.NoteDataSource
import github.debian17.domain.base.CoroutineUseCase

class DeleteNote(
    private val noteDataSource: NoteDataSource
) : CoroutineUseCase<DeleteNote.Params, Unit>() {

    override suspend fun execute(params: Params) {
        noteDataSource.deleteNote(params.id)
    }

    class Params(val id: Int)

}