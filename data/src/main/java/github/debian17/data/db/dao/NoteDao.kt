package github.debian17.data.db.dao

import androidx.room.*
import github.debian17.data.db.model.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteModel)

    @Update
    fun update(note: NoteModel)

    @Query("DELETE FROM Notes WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM Notes")
    suspend fun getAll(): List<NoteModel>

}