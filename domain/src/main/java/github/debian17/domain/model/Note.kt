package github.debian17.domain.model

import org.threeten.bp.LocalDateTime

class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDateTime
)