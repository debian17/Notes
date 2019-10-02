package github.debian17.data.db.model

import github.debian17.data.db.converter.LocalDateTimeConverter
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.threeten.bp.LocalDateTime

@Entity(tableName = "Notes")
data class NoteModel(
    val title: String,
    val content: String,
    @TypeConverters(LocalDateTimeConverter::class)
    val date: LocalDateTime
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}