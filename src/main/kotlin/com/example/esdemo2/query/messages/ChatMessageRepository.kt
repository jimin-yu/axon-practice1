package com.example.esdemo2.query.messages

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessage, Long> {
    fun findAllByRoomIdOrderByTimestamp(roomId: String): List<ChatMessage>
}