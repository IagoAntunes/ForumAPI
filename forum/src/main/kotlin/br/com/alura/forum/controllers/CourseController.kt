package br.com.alura.forum.controllers

import br.com.alura.forum.dtos.CreateCourseRequestDto
import br.com.alura.forum.models.Course
import br.com.alura.forum.services.CourseService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController()
@RequestMapping("/courses")
class CourseController(
    private val courseService: CourseService,

) {
    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid request: CreateCourseRequestDto,
        uriBuilder: UriComponentsBuilder
    ) :  ResponseEntity<Course>{
        val course = courseService.createCourse(request)
        val uri = uriBuilder.path("/courses/${course?.id}").build().toUri()
        return ResponseEntity.created(uri).body(course);
    }

    @GetMapping
    fun getAll() : List<Course> {
        return courseService.getAll()
    }

}