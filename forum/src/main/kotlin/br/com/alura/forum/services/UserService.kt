package br.com.alura.forum.services

import br.com.alura.forum.models.Usuario
import br.com.alura.forum.repositories.IUserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository:IUserRepository) {

    init {

    }

    fun getAuthorById(userId: Long): Usuario {
        return userRepository.getOne(userId)
    }
}
