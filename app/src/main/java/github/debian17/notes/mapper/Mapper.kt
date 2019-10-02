package github.debian17.notes.mapper

interface Mapper<A, B> {

    fun map(obj: A): B

}