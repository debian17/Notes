package github.debian17.data.db.dao

import androidx.room.*
import github.debian17.data.db.model.NoteModel
import io.reactivex.Flowable

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteModel)

    @Update
    fun update(note: NoteModel)

    @Delete
    fun delete(note: NoteModel)

    @Query("SELECT * FROM Notes")
    fun getAll(): Flowable<List<NoteModel>>

}