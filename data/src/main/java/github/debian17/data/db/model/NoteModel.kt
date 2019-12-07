package github.debian17.data.db.model

import github.debian17.data.db.converter.LocalDateTimeConverter
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import github.debian17.data.db.converter.ImagesConverter
import github.debian17.data.db.converter.RecordsConverter
import org.threeten.bp.LocalDateTime

@Entity(tableName = "Notes")
data class NoteModel(
    val title: String,
    val content: String,
    @TypeConverters(LocalDateTimeConverter::class)
    val dateOfCreation: LocalDateTime,
    val isDeleted: Boolean,
    @TypeConverters(ImagesConverter::class)
    val images: List<String>,
    @TypeConverters(RecordsConverter::class)
    val records: List<String>
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}