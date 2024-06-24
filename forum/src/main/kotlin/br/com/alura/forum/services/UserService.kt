package br.com.alura.forum.services

import br.com.alura.forum.models.User
import org.springframework.stereotype.Service


@Service
class UserService(var authors: MutableList<User> = ArrayList()) {

    init {
        authors = mutableListOf(
            User(
                id = 1,
                name = "Rafael",
                email = "rafael@gmail.com"
            ),
            User(
                id = 2,
                name = "Joao",
                email = "joao@gmail.com"
            )
        )
    }

    fun getAuthorById(userId: Long): User {
        return authors.first{
            it.id == userId
        }
    }
}
