package br.com.alura.forum.controllers

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class UpdateTopicDto (
    @field:NotNull val id: Long,
    @field:NotEmpty val title: String,
    @field:NotEmpty val message: String
)
