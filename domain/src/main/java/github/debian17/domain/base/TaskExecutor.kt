package github.debian17.domain.base

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.*

object TaskExecutor {

    private const val KEEP_ALIVE_TIME = 15L

    private val mainThread = Handler(Looper.getMainLooper())

    private lateinit var executor: ThreadPoolExecutor

    fun init() {
        val processors = Runtime.getRuntime().availableProcessors()
        val workQueue: BlockingQueue<Runnable> = LinkedBlockingQueue()
        executor = ThreadPoolExecutor(
            processors,
            processors * 2,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            workQueue
        )
    }

    fun <T> execute(task: Callable<T>, callback: ResultCallback<T>): CancelableTask {
        val future = executor.submit {
            val result: T
            try {
                result = task.call()
            } catch (e: Throwable) {
                if (e is InterruptedException) {
                    //return if task was canceled
                    return@submit
                } else {
                    mainThread.post {
                        callback.onError(e)
                    }
                    return@submit
                }
            }
            mainThread.post {
                callback.onSuccess(result)
            }
        }
        return CancelableTask(future)
    }

    fun execute(task: Runnable, callback: CompletableCallback): CancelableTask {
        val future = executor.submit {
            try {
                task.run()
            } catch (t: Throwable) {
                if (t is InterruptedException) {
                    //return if task was canceled
                    return@submit
                } else {
                    mainThread.post {
                        callback.onError(t)
                    }
                    return@submit
                }
            }
            mainThread.post {
                callback.onComplete()
            }
        }
        return CancelableTask(future)
    }

    fun <T1, T2> invokeAll(
        t1: Callable<T1>,
        t2: Callable<T2>,
        callback: ResultFunc2<T1, T2>
    ): CancelableTask? {
        val future = executor.submit {
            val f1: Future<T1>
            val f2: Future<T2>
            try {
                f1 = executor.submit(t1)
                f2 = executor.submit(t2)
                while (!f1.isDone && !f2.isDone) {
                    //wait until all tasks ends
                }
            } catch (t: Throwable) {
                Log.e("MyTag", "local catch")
                mainThread.post {
                    callback.onError(t)
                }
                return@submit
            }
            mainThread.post {
                callback.onSuccess(f1.get(), f2.get())
            }
        }
        return CancelableTask(future)
    }

}