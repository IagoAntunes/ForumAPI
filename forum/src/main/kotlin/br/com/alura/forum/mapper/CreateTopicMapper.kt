package br.com.alura.forum.mapper

import br.com.alura.forum.dtos.CreateTopicDto
import br.com.alura.forum.models.Topic
import br.com.alura.forum.services.CourseService
import br.com.alura.forum.services.UserService
import org.springframework.stereotype.Component

@Component
class CreateTopicMapper(
    private val courseService: CourseService,
    private  val authorService: UserService,
    ) : Mapper<CreateTopicDto, Topic> {
    override fun map(t: CreateTopicDto): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.getCourseById(t.courseId),
            author = authorService.getAuthorById(t.userId)
        )
    }
}