package br.com.alura.forum.services

import br.com.alura.forum.models.Course
import br.com.alura.forum.repositories.ICourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(var couseRepository : ICourseRepository) {

    fun getCourseById(id: Long) : Course{
        return couseRepository.getOne(id)
    }

}
