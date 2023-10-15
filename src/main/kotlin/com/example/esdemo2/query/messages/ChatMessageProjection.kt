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
        System.out.println("message posted : " + evt.participant + evt.roomId + evt.message)
        val chatMessage = ChatMessage(evt.roomId, evt.participant, evt.message, timestamp.toEpochMilli())
        System.out.println("chat message :" + chatMessage.participant + chatMessage.roomId + chatMessage.message)
        repository.save(chatMessage)
    }


    // Read db에서 조회
    @QueryHandler
    fun handle(query: RoomMessagesQuery): List<ChatMessage> {
        return repository.findAllByRoomIdOrderByTimestamp(query.roomId)
    }
}