package github.debian17.notes

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import github.debian17.domain.base.TaskExecutor
import github.debian17.domain.injector.NotesInjector

class App : Application() {

    companion object {
        private const val DATABASE_NAME = "notes_db"
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        NotesInjector.init(this, DATABASE_NAME)

        TaskExecutor.init()

    }

}