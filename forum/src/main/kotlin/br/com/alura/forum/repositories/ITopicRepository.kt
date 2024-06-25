package br.com.alura.forum.repositories

import br.com.alura.forum.dtos.TopicByCategoryDto
import br.com.alura.forum.models.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ITopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(nameCourse: String,pgination:Pageable): Page<Topic>

    @Query("SELECT new br.com.alura.forum.dtos.TopicByCategoryDto(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report() : List<TopicByCategoryDto>
}