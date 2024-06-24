package br.com.alura.forum.models

import java.time.LocalDateTime

data class ResponseMessage(
    val id:Long? = null,
    val message: String,
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic,
    val solution: Boolean
)
