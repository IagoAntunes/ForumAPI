package br.com.alura.forum.services
import br.com.alura.forum.controllers.UpdateTopicDto
import br.com.alura.forum.dtos.CreateTopicDto
import br.com.alura.forum.dtos.ResponseTopicDto
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mapper.CreateTopicMapper
import br.com.alura.forum.mapper.ResponseTopicMapper
import br.com.alura.forum.models.Topic
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class TopicService(
    private  var topics : MutableList<Topic> = ArrayList(),
    private  val topicMapper: ResponseTopicMapper,
    private  val createTopicMapper: CreateTopicMapper
) {
    fun list(): List<ResponseTopicDto>{
        return topics.stream().map{
            topic-> topicMapper.map(topic)
        }.collect(Collectors.toList())
    }

    fun getTopicById(id: Long): ResponseTopicDto {
        val topic = topics.filter {
            it.id == id
        }.first()
        return topicMapper.map(topic)
    }

    fun createTopic(topicDto: CreateTopicDto) : ResponseTopicDto{
        val topic = createTopicMapper.map(topicDto)
        topic.id = topics.size.toLong() + 1
        topics.add(topic)
        return topicMapper.map(topic);
    }

    fun updateTopic(request: UpdateTopicDto) : ResponseTopicDto{
        val topic: Topic?
         topic = topics.first {
            it.id == request.id
        }

        topics.remove(topic)
        val newTopic = Topic(
            id = request.id,
            title = request.title,
            message = request.message,
            course = topic.course,
            author = topic.author,
            responses = topic.responses,
            status = topic.status,
            dateCreated = topic.dateCreated
        )
        topics.add(newTopic)

        return topicMapper.map(newTopic)
    }

    fun deleteTopic(id: Long) {
        val topic = topics.stream().filter() {
             it.id == id
         }.findFirst().orElseThrow{NotFoundException("Topic not found")}
         topics.remove(topic)
    }
}