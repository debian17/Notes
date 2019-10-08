package github.debian17.domain.mapper

interface Mapper<A, B> {

    fun map(obj: A): B

}