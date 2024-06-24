package br.com.alura.forum.controllers

import br.com.alura.forum.models.Course
import br.com.alura.forum.models.StatusTopic
import br.com.alura.forum.models.Topic
import br.com.alura.forum.models.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    @GetMapping
    fun hello(): List<Topic> {
        val topic = Topic(
            1,
            "Dúvida",
            "Descrição",
            course = Course(
                1,
                "Kotlin",
                "Programming"
            ),
            author = User(
                1,
                "Joao",
                "joao@gmail.com"
            ),
            status = StatusTopic.NOT_ANSWERED,
        )

        return listOf(topic, topic, topic)

    }

}