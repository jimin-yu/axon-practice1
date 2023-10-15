package com.example.esdemo2.query.participants

import com.example.esdemo2.coreapi.ParticipantJoinedRoomEvent
import com.example.esdemo2.coreapi.ParticipantLeftRoomEvent
import com.example.esdemo2.coreapi.RoomParticipantsQuery
import com.example.esdemo2.query.messages.ChatMessage
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class RoomParticipantProjection(private val repository: RoomParticipantRepository) {

    @EventHandler
    fun on(evt: ParticipantJoinedRoomEvent){
        val participant = RoomParticipant(evt.roomId, evt.participant)
        repository.save(participant)
    }

    @EventHandler
    fun on(evt: ParticipantLeftRoomEvent) {
        repository.deleteByParticipantAndRoomId(evt.roomId, evt.participant)
    }

    @QueryHandler
    fun handle(query: RoomParticipantsQuery): List<RoomParticipant> {
        return repository.findRoomParticipantsByRoomId(query.roomId)
    }
}