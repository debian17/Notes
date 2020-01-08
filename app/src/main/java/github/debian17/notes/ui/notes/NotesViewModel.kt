package github.debian17.notes.ui.notes

import android.os.Handler
import android.util.Log
import androidx.lifecycle.*
import github.debian17.domain.base.CompletableCallback
import github.debian17.domain.base.ResultCallback
import github.debian17.domain.base.ResultFunc2
import github.debian17.domain.base.TaskExecutor
import github.debian17.domain.model.Note
import github.debian17.domain.notes.AddNote
import github.debian17.notes.base.mvvm.BaseViewModel
import github.debian17.domain.notes.GetNotes
import kotlinx.coroutines.*
import org.threeten.bp.LocalDateTime
import java.lang.Exception
import java.util.concurrent.Callable

class NotesViewModel(
    private val getNotes: GetNotes,
    private val addNote: AddNote
) : BaseViewModel() {

    private val notes = MutableLiveData<List<Note>>()

    init {
        getNotes()
    }

    private fun getNotes() {
        isLoading.value = true

//        val start = System.currentTimeMillis()
//        val tasks = TaskExecutor.executeAll(
//            Callable<Int> {
//                Thread.sleep(8000L)
//                Log.e("MyTag", "Int task completed")
//                //throw RuntimeException("Test")
//                return@Callable 21
//            }, Callable<String> {
//                Thread.sleep(3000L)
//                Log.e("MyTag", "String task completed")
//                return@Callable "Kek"
//            }, object : ResultFunc2<Int, String> {
//                override fun onSuccess(t1: Int, t2: String) {
//                    val end = System.currentTimeMillis()
//                    val result = (end - start) / 1000L
//                    Log.e("MyTag", "TIME = $result")
//                    Log.e("MyTag", "onSuccess: t1 = $t1, t2 = $t2")
//                }
//
//                override fun onError(t: Throwable) {
//                    Log.e("MyTag", "onError = ${t.message}")
//                }
//            })
//        runningTasks.add(tasks)

//        val task1 = TaskExecutor.execute(Callable<Int> {
//            Log.e("MyTag", "execute thread = ${Thread.currentThread().name}")
//            Thread.sleep(5000L)
//            //throw RuntimeException("Test")
//            return@Callable 21
//        }, object : ResultCallback<Int> {
//            override fun onSuccess(result: Int) {
//                isLoading.value = false
//                Log.e("MyTag", "onSuccess thread = ${Thread.currentThread().name}")
//                Log.e("MyTag", "onSuccess result = $result")
//            }
//
//            override fun onError(t: Throwable) {
//                isLoading.value = false
//                Log.e("MyTag", "onError thread = ${Thread.currentThread().name}")
//                Log.e("MyTag", "error = $t")
//            }
//        })
//        runningTasks.add(task1)

//        val task2 = TaskExecutor.execute(Runnable {
//            Log.e("MyTag", "execute thread = ${Thread.currentThread().name}")
//            Thread.sleep(5000L)
//        }, object : CompletableCallback {
//            override fun onComplete() {
//                isLoading.value = false
//                Log.e("MyTag", "onSuccess thread = ${Thread.currentThread().name}")
//                Log.e("MyTag", "onComplete")
//            }
//
//            override fun onError(t: Throwable) {
//                isLoading.value = false
//                Log.e("MyTag", "onError thread = ${Thread.currentThread().name}")
//                Log.e("MyTag", "error = ${t.message}")
//            }
//        })
//        runningTasks.add(task2)

//        Handler().postDelayed({
//            tasks.cancel(true)
//            Log.e("MyTag", "task.isCancelled = ${tasks.isCancelled()}")
//        }, 2000L)

//        viewModelScope.launch {
//            isLoading.value = true
//            try {
//                val params = GetNotes.Params()
//                val loadedNotes = getNotes.execute(params)
//                notes.value = loadedNotes
//            } catch (e: Exception) {
//                isError.value = e
//            } finally {
//                isLoading.value = false
//            }
//        }
    }

    fun addNote(
        title: String,
        content: String,
        images: List<String>?,
        records: List<String>?
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val params = AddNote.Params(
                    title = title,
                    content = content,
                    dateOfCreation = LocalDateTime.now(),
                    isDeleted = false,
                    images = images,
                    records = records
                )
                addNote.execute(params)
            } catch (e: Exception) {
                isError.value = e
            } finally {
                isLoading.value = false
            }
        }

    }

    fun observeNotes(owner: LifecycleOwner, observer: Observer<List<Note>>) {
        notes.observe(owner, observer)
    }

    @Suppress("UNCHECKED_CAST")
    class NotesViewModelFactory(
        private val getNotes: GetNotes,
        private val addNote: AddNote
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NotesViewModel(getNotes, addNote) as T
        }
    }

}
