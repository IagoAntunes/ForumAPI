package br.com.alura.forum.controllers

import br.com.alura.forum.dtos.CreateUserRequestDto
import br.com.alura.forum.dtos.CreateUserResponseDto
import br.com.alura.forum.dtos.GetUserResponseDto
import br.com.alura.forum.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/users")
class UserController(
    val userService:UserService
) {

    @PostMapping
    fun create(
        @RequestBody request: CreateUserRequestDto,
        uriBuilder: UriComponentsBuilder) : ResponseEntity<CreateUserResponseDto>{
        val user = userService.createUser(request)
        val uri = uriBuilder.path("/users/{id}").buildAndExpand(1).toUri()
        return ResponseEntity.created(uri).body(user)
    }

    @GetMapping
    fun getAll() : ResponseEntity<List<GetUserResponseDto>>{
        return userService.getAllUsers().let {
            ResponseEntity.ok(it)
        }
    }

}