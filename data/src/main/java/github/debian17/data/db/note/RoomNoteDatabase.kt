package github.debian17.data.db.note

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.debian17.data.db.converter.ImagesConverter
import github.debian17.data.db.converter.LocalDateTimeConverter
import github.debian17.data.db.dao.NoteDao
import github.debian17.data.db.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class, ImagesConverter::class)
abstract class RoomNoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

}