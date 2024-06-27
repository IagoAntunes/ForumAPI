package br.com.alura.forum.models

data class Credentials(
    val username: String,
    val password: String
){
    constructor() : this("", "") // Construtor padrão vazio
}
