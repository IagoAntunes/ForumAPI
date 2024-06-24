package br.com.alura.forum.mapper

import br.com.alura.forum.dtos.ResponseTopicDto
import br.com.alura.forum.models.Topic
import org.springframework.stereotype.Component

@Component
class ResponseTopicMapper : Mapper<Topic,ResponseTopicDto>{
    override fun map(t: Topic): ResponseTopicDto {
       return ResponseTopicDto(
           id = t.id,
           title = t.title,
           message = t.message,
           status = t.status,
           date = t.dateCreated
       )
    }
}