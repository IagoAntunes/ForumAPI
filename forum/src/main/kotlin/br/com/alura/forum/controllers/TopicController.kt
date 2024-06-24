package br.com.alura.forum.controllers
import br.com.alura.forum.dtos.CreateTopicDto
import br.com.alura.forum.dtos.ResponseTopicDto
import br.com.alura.forum.services.TopicService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/topics")
class TopicController(
    private  val service: TopicService
){
    @GetMapping
    fun getAll(): List<ResponseTopicDto> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long): ResponseTopicDto {
        return service.getTopicById(id)
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid request: CreateTopicDto,
       uriBuilder: UriComponentsBuilder
    ) : ResponseEntity<ResponseTopicDto>{
        val topicCreated = service.createTopic(request)
        val uri = uriBuilder.path("/topics/${topicCreated.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(topicCreated);
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid request: UpdateTopicDto) : ResponseEntity<ResponseTopicDto>{
        val topicUpdated = service.updateTopic(request)

        return ResponseEntity.ok(topicUpdated)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id:Long) {
        service.deleteTopic(id)
    }
}