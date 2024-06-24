package br.com.alura.forum.dtos

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateTopicDto(
    @field:NotEmpty @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres") val title: String,
    @field:NotEmpty val message: String,
    @field:NotNull val courseId: Long,
    @field:NotNull val userId: Long
)