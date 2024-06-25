package br.com.alura.forum.repositories

import br.com.alura.forum.models.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ITopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(nameCourse: String,pgination:Pageable): Page<Topic>
}