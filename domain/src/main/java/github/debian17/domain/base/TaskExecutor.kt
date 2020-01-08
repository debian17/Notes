package github.debian17.domain.base

import android.os.Handler
import android.os.Looper
import java.util.concurrent.*

object TaskExecutor {

    private const val KEEP_ALIVE_TIME = 15L

    private lateinit var mainThread: Handler

    private lateinit var executor: ThreadPoolExecutor

    fun init() {
        mainThread = Handler(Looper.getMainLooper())

        val processors = Runtime.getRuntime().availableProcessors()
        val workQueue = LinkedBlockingQueue<Runnable>()
        executor = ThreadPoolExecutor(
            processors,
            processors * 2,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            workQueue
        )
    }

    fun <T> execute(task: Callable<T>, callback: ResultCallback<T>): CancelableTask {
        val tasks = ArrayList<Future<*>>()
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
        tasks.add(future)
        return CancelableTask(tasks)
    }

    fun execute(task: Runnable, callback: CompletableCallback): CancelableTask {
        val tasks = ArrayList<Future<*>>()
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
        tasks.add(future)
        return CancelableTask(tasks)
    }

    fun <T1, T2> executeAll(
        task1: Callable<T1>,
        task2: Callable<T2>,
        callback: ResultFunc2<T1, T2>
    ): CancelableTask {
        val tasks = ArrayList<Future<*>>()
        val future = executor.submit {
            val result1: T1
            val result2: T2
            try {
                val future1 = executor.submit(task1)
                val future2 = executor.submit(task2)

                tasks.add(future1)
                tasks.add(future2)

                while (!future1.isDone && !future2.isDone) {
                    //wait until all tasks ends
                }

                result1 = future1.get()
                result2 = future2.get()

            } catch (t: Throwable) {
                when (t) {
                    is CancellationException -> return@submit
                    is InterruptedException -> return@submit
                    else -> {
                        mainThread.post {
                            callback.onError(t)
                        }
                        return@submit
                    }
                }
            }
            mainThread.post {
                callback.onSuccess(result1, result2)
            }
        }
        tasks.add(future)
        return CancelableTask(tasks)
    }

}