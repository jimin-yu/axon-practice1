package com.example.esdemo2.query.summary

import com.example.esdemo2.coreapi.AllRoomsQuery
import com.example.esdemo2.coreapi.RoomCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class RoomSummaryProjection(private val repository: RoomSummaryRepository) {

    @EventHandler
    fun on(evt: RoomCreatedEvent) {
        System.out.println("!!!!!!!!!!!!!!!!!!!! EVENT HANDLER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        val summary = RoomSummary(evt.roomId, evt.name)
        repository.save(summary)
    }

    @QueryHandler
    fun handle(query: AllRoomsQuery): List<RoomSummary> {
        return repository.findAll()
    }
}