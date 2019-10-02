package github.debian17.notes

import android.app.Application
import androidx.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen
import github.debian17.data.db.note.RoomNoteDatabase
import github.debian17.notes.base.mvvm.BaseFragment
import github.debian17.notes.di.component.DaggerDataComponent
import github.debian17.notes.di.component.DaggerViewModelComponent
import github.debian17.notes.di.component.DataComponent
import github.debian17.notes.di.component.ViewModelComponent
import github.debian17.notes.di.module.DatabaseModule

class App : Application() {

    companion object {
        private const val DATABASE_NAME = "notes_db"
    }

    private lateinit var dataComponent: DataComponent
    private lateinit var viewModelComponent: ViewModelComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        val roomNoteDatabase = Room.databaseBuilder(
            this,
            RoomNoteDatabase::class.java,
            DATABASE_NAME
        ).build()

        val databaseModule = DatabaseModule(roomNoteDatabase)

        dataComponent = DaggerDataComponent.builder()
            .databaseModule(databaseModule)
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .databaseModule(databaseModule)
            .build()

    }

    fun getDataComponent(): DataComponent {
        return dataComponent
    }

    fun getViewModelComponent(): ViewModelComponent {
        return viewModelComponent
    }

}