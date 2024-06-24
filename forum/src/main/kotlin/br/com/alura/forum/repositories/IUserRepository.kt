package br.com.alura.forum.repositories

import br.com.alura.forum.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface IUserRepository  : JpaRepository<Usuario,Long>{
}