package github.debian17.domain.base

import java.util.concurrent.Future

class CancelableTask(private val future: List<Future<*>>) {

    fun cancel(mayInterruptIfRunning: Boolean) {
        future.forEach {
            if (!it.isDone) {
                it.cancel(mayInterruptIfRunning)
            }
        }
    }

    fun isCancelled(): Boolean {
        return future.all { it.isCancelled }
    }

    fun isDone(): Boolean {
        return future.all { it.isDone }
    }

}