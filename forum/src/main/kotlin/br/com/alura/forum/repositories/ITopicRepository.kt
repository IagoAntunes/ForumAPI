package br.com.alura.forum.repositories

import br.com.alura.forum.models.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface ITopicRepository : JpaRepository<Topic, Long> {

}