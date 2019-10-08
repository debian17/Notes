package github.debian17.data.db.converter

import androidx.room.TypeConverter
import java.lang.StringBuilder

object ImagesConverter {

    @TypeConverter
    @JvmStatic
    fun fromListToString(images: List<String>): String {
        if (images.isEmpty()) {
            return ""
        } else {
            val result = StringBuilder()
            images.forEach {
                result.append(it)
                    .append(",")
            }
            return result.toString()
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToList(images: String): List<String> {
        if (images.isEmpty()) {
            return emptyList()
        } else {
            return images.split(",")
        }
    }

}