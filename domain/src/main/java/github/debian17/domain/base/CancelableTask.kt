package github.debian17.domain.base

import java.util.concurrent.Future

class CancelableTask(private val future: Future<*>) {

    fun cancel(mayInterruptIfRunning: Boolean) {
        future.cancel(mayInterruptIfRunning)
    }

    fun isCancelled(): Boolean {
        return future.isCancelled
    }

    fun isDone(): Boolean {
        return future.isDone
    }

}