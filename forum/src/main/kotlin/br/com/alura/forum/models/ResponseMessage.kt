package br.com.alura.forum.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class ResponseMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null,
    val message: String,
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val author: Usuario,
    @ManyToOne
    val topic: Topic,
    val solution: Boolean
)
