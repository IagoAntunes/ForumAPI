package br.com.alura.forum.models

import java.time.LocalDateTime

data class Topic(
    var id: Long? = null,
    val title: String,
    val message: String,
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: StatusTopic = StatusTopic.NOT_ANSWERED,
    val responses: List<ResponseMessage> = ArrayList()
)