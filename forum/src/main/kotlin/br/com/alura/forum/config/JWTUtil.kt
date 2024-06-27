package br.com.alura.forum.config

import br.com.alura.forum.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JWTUtil(
    private  val userService: UserService
) {
    private val expiration:Long = 1800000
    private val secretKey: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    @Value("\${jwt.secret}")
    private lateinit var secret:String

    fun generateToken(username: String, authorities: List<br.com.alura.forum.models.Role>) : String?{
        return Jwts.builder()
            .setSubject(username)
            .claim("role",authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secretKey)
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt);
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).body.subject;
        val user = userService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}