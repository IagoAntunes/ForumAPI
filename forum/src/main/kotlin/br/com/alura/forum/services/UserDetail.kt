package br.com.alura.forum.services
import br.com.alura.forum.models.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val user:Usuario) : UserDetails{
    override fun getAuthorities() = user.role

    override fun getPassword() = user.password

    override fun getUsername() = user.email
}