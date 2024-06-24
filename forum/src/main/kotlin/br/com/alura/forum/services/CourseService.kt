package br.com.alura.forum.services

import br.com.alura.forum.models.Course
import org.springframework.stereotype.Service

@Service
class CourseService(var courses : MutableList<Course>) {

    init {
        courses = mutableListOf(
            Course(
                id = 1,
                name = "Kotlin",
                category = "Programming"
            ),
            Course(
                id = 2,
                name = "Java",
                category = "Programming"
            ),
            Course(
                id = 3,
                name = "HTML",
                category = "Web"
            )
        )
    }

    fun getCourseById(id: Long) : Course{
        return courses.first { it.id == id }
    }

}
