package github.debian17.notes.di.module

import dagger.Module
import dagger.Provides
import github.debian17.data.db.model.NoteModel
import github.debian17.notes.mapper.Mapper
import github.debian17.notes.mapper.NoteMapper
import github.debian17.notes.model.Note

@Module
class MapperModule {

    @Provides
    fun provideNoteMapper(): Mapper<NoteModel, Note> {
        return NoteMapper()
    }

}