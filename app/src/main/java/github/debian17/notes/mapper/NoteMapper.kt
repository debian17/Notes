package github.debian17.notes.mapper

import github.debian17.data.db.model.NoteModel
import github.debian17.notes.model.Note

class NoteMapper : Mapper<NoteModel, Note> {

    override fun map(obj: NoteModel): Note {
        return Note(obj.id, obj.title, obj.content, obj.date)
    }

}