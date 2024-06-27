package br.com.alura.forum.config

import br.com.alura.forum.models.Credentials
import br.com.alura.forum.services.UserDetail
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {

    //Essa função validara o login
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        println(request?.inputStream)
        val(username,password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username,password)
        return authManager.authenticate(token)
    }

    //Essa função ira gerar o token devolver para o cliente
    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetail)
        val token = jwtUtil.generateToken(user.username,user.authorities)
        response?.addHeader("Authorization","Bearer $token")
    }
}
