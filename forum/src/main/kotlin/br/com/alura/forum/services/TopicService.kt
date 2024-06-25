package br.com.alura.forum.services
import br.com.alura.forum.dtos.UpdateTopicDto
import br.com.alura.forum.dtos.CreateTopicDto
import br.com.alura.forum.dtos.ResponseTopicDto
import br.com.alura.forum.dtos.TopicByCategoryDto
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mapper.CreateTopicMapper
import br.com.alura.forum.mapper.ResponseTopicMapper
import br.com.alura.forum.models.Topic
import br.com.alura.forum.repositories.ITopicRepository
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private  var topicRepository : ITopicRepository,
    private  val topicMapper: ResponseTopicMapper,
    private  val createTopicMapper: CreateTopicMapper,
    private val em: EntityManager,
) {
    fun getAll(
       nameCourse:String?,
       pagination: Pageable
    ): Page<ResponseTopicDto> {
        val topics = if(nameCourse == null){
            topicRepository.findAll(pagination)
        } else {
            topicRepository.findByCourseName(nameCourse,pagination)
        }
        return topics.map {
            t ->
            topicMapper.map(t)
        }
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

    fun report() : List<TopicByCategoryDto> {
        return topicRepository.report()
    }
}