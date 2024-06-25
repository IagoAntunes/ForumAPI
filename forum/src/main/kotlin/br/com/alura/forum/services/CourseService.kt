package br.com.alura.forum.services

import br.com.alura.forum.dtos.CreateCourseRequestDto
import br.com.alura.forum.models.Course
import br.com.alura.forum.repositories.ICourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(var courseRepository : ICourseRepository) {

    fun getCourseById(id: Long) : Course{
        return courseRepository.getOne(id)
    }

    fun getAll() : List<Course>{
        return courseRepository.findAll()
    }

    fun createCourse(request: CreateCourseRequestDto): Course? {
        val course = courseRepository.save(Course(name = request.name, category = request.category))
        return course;
    }

}
