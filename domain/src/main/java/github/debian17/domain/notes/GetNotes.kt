package github.debian17.domain.notes

import github.debian17.data.db.model.NoteModel
import github.debian17.data.source.NoteDataSource
import github.debian17.domain.base.FlowableUseCase
import github.debian17.domain.mapper.Mapper
import github.debian17.domain.model.Note
import io.reactivex.Flowable

class GetNotes(
    private val notesDataSource: NoteDataSource,
    private val mapper: Mapper<NoteModel, Note>
) :
    FlowableUseCase<GetNotes.Params, List<Note>>() {

    override fun execute(params: Params): Flowable<List<Note>> {
        return notesDataSource.getNotes()
            .map { list ->
                return@map list.map { item -> mapper.map(item) }
            }
    }

    class Params
}