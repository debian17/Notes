package github.debian17.domain.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

abstract class UseCase<in In, out Out> {
    abstract fun execute(params: In): Out
}

abstract class FlowableUseCase<In, Out> : UseCase<In, Flowable<Out>>()

abstract class SingleUseCase<In, Out> : UseCase<In, Single<Out>>()

abstract class CompletableUseCase<In> : UseCase<In, Completable>()