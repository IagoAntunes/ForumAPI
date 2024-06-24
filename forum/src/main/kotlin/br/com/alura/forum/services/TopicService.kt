package br.com.alura.forum.services
import br.com.alura.forum.controllers.UpdateTopicDto
import br.com.alura.forum.dtos.CreateTopicDto
import br.com.alura.forum.dtos.ResponseTopicDto
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mapper.CreateTopicMapper
import br.com.alura.forum.mapper.ResponseTopicMapper
import br.com.alura.forum.models.Topic
import br.com.alura.forum.repositories.ITopicRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class TopicService(
    private  var topicRepository : ITopicRepository,
    private  val topicMapper: ResponseTopicMapper,
    private  val createTopicMapper: CreateTopicMapper
) {
    fun list(): List<ResponseTopicDto>{
        return topicRepository.findAll().stream().map {
            t ->
            topicMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun getTopicById(id: Long): ResponseTopicDto {
        val topic = topicRepository.findById(id).orElseThrow{NotFoundException("Topic not found")}
        return topicMapper.map(topic)
    }

    fun createTopic(topicDto: CreateTopicDto) : ResponseTopicDto{
        val topic = createTopicMapper.map(topicDto)
        topicRepository.save(topic)
        return topicMapper.map(topic);
    }

    fun updateTopic(request: UpdateTopicDto) : ResponseTopicDto{
        val topic: Topic? = topicRepository.findById(request.id).orElseThrow{NotFoundException("Topic not found")}

        topic!!.title = request.title
        topic.message = request.message

        return topicMapper.map(topic)
    }

    fun deleteTopic(id: Long) {
        topicRepository.deleteById(id)
    }
}