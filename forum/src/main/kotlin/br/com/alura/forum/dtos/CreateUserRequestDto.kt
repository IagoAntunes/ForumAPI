package br.com.alura.forum.dtos

data class CreateUserRequestDto (
    val name:String,
    val email:String,
    val password:String,
)