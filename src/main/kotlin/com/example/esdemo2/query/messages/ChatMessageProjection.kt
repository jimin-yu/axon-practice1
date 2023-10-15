package com.example.esdemo2.query.messages

import com.example.esdemo2.coreapi.MessagePostedEvent
import com.example.esdemo2.coreapi.RoomMessagesQuery
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.Timestamp
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class ChatMessageProjection(private val repository: ChatMessageRepository) {

    // Read db 업데이트
    @EventHandler
    fun on(evt: MessagePostedEvent, @Timestamp timestamp: Instant) {
        val chatMessage = ChatMessage(evt.roomId, evt.participant, evt.message, timestamp.toEpochMilli())
        repository.save(chatMessage)
    }


    // Read db에서 조회
    @QueryHandler
    fun handle(query: RoomMessagesQuery): List<ChatMessage> {
        return repository.findAllByRoomIdOrderByTimestamp(query.roomId)
    }
}