package github.debian17.data.db.converter

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

object LocalDateTimeConverter {

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTimeToLong(date: LocalDateTime?): String? {
        if (date == null) {
            return null
        } else {
            return date.toString()
            //return date.toEpochSecond(ZoneOffset.ofTotalSeconds(0))
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromLongToLocalDateTime(date: String?): LocalDateTime? {
        if (date == null) {
            return null
        } else {
            return LocalDateTime.parse(date)
            //return LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.ofTotalSeconds(0))
        }
    }

}