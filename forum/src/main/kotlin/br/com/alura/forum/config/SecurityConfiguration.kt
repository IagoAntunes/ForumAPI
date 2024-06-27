package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private  val jwtUtil: JWTUtil,
    private val configuration: AuthenticationConfiguration,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity, authenticationManagerBuilder: AuthenticationManagerBuilder): SecurityFilterChain {
        return http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests {
//                it.requestMatchers("/topics")
//                    .hasAuthority("Reading_Writing")
                it.requestMatchers(HttpMethod.POST,"/login")
                    .permitAll()
                it.anyRequest()
                    .authenticated()
            }
            .addFilterBefore(
                JwtLoginFilter(authManager = configuration.authenticationManager,jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter().javaClass
            )
            .addFilterBefore(
                JwtAuthenticationFilter(jwtUtil= jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.build()
    }
    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()
}