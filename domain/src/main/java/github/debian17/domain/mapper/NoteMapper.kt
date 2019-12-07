package github.debian17.domain.mapper

import github.debian17.data.db.model.NoteModel
import github.debian17.domain.model.Note

class NoteMapper : Mapper<NoteModel, Note> {

    override fun map(obj: NoteModel): Note {
        return Note(obj.id, obj.title, obj.content, obj.dateOfCreation)
    }

}