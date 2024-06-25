package br.com.alura.forum.services

import br.com.alura.forum.dtos.CreateUserRequestDto
import br.com.alura.forum.dtos.CreateUserResponseDto
import br.com.alura.forum.dtos.GetUserResponseDto
import br.com.alura.forum.models.Usuario
import br.com.alura.forum.repositories.IUserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository:IUserRepository) {

    fun getAuthorById(userId: Long): Usuario {
        return userRepository.getOne(userId)
    }

    fun getAllUsers() : List<GetUserResponseDto>{
        val users = userRepository.findAll().map {
            GetUserResponseDto(it.name, it.email)
        }
        return users
    }

    fun createUser(request: CreateUserRequestDto): CreateUserResponseDto {
        val userCreated = userRepository.save(Usuario( name = request.name,email = request.email))
        return CreateUserResponseDto(userCreated.name, userCreated.email)
    }
}
