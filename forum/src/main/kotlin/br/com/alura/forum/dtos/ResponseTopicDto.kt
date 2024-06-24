package br.com.alura.forum.dtos

import br.com.alura.forum.models.StatusTopic
import java.time.LocalDateTime

data class ResponseTopicDto(
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val date: LocalDateTime,
)
