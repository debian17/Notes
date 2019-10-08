package github.debian17.data.db.converter

import androidx.room.TypeConverter
import java.lang.StringBuilder

object RecordsConverter {

    @TypeConverter
    @JvmStatic
    fun fromListToString(records: List<String>): String {
        if (records.isEmpty()) {
            return ""
        } else {
            val result = StringBuilder()
            records.forEach {
                result.append(it)
                    .append(",")
            }
            return result.toString()
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToList(records: String): List<String> {
        if (records.isEmpty()) {
            return emptyList()
        } else {
            return records.split(",")
        }
    }

}