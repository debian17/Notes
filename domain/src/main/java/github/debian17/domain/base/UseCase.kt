package github.debian17.domain.base


abstract class CoroutineUseCase<in In, out Out> {
    abstract suspend fun execute(params: In): Out
}