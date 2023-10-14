package com.example.esdemo2.aggregate

import com.example.esdemo2.coreapi.CreateRoomCommand
import com.example.esdemo2.coreapi.RoomCreatedEvent
import mu.KotlinLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ChatRoom {
    private val logger = KotlinLogging.logger {}

    @AggregateIdentifier
    private var roomId: String? = null
    private var name: String? = null
    private val participants: MutableSet<String> = HashSet()


    @CommandHandler
    constructor(cmd: CreateRoomCommand) {
        logger.info { "handle command"}
        // business logic & validation
        AggregateLifecycle.apply(RoomCreatedEvent(cmd.roomId, cmd.name))
    }

    @EventSourcingHandler
    fun on(evt: RoomCreatedEvent) {
        logger.info { "event sourcing handler"}
        roomId = evt.roomId
        name = evt.name
    }

//    @CommandHandler
//    fun handle(cmd: JoinRoomCommand) {
//        if (!participants.contains(cmd.participant)) {
//            AggregateLifecycle.apply(ParticipantJoinedRoomEvent(cmd.roomId, cmd.participant))
//        }
//    }
//
//    @EventSourcingHandler
//    fun on(evt: ParticipantJoinedRoomEvent) {
//        participants.add(evt.participant)
//    }
//
//    @CommandHandler
//    fun handle(cmd: LeaveRoomCommand) {
//        if (participants.contains(cmd.participant)) {
//            AggregateLifecycle.apply(ParticipantLeftRoomEvent(cmd.roomId, cmd.participant))
//        }
//    }
//
//    @EventSourcingHandler
//    fun on(evt: ParticipantLeftRoomEvent) {
//        participants.remove(evt.participant)
//    }
//
//    @CommandHandler
//    fun handle(cmd: PostMessageCommand) {
//        if (!participants.contains(cmd.participant)) throw IllegalStateException("[post message fail] participant is not in the room..")
//        AggregateLifecycle.apply(MessagePostedEvent(cmd.roomId, cmd.participant, cmd.message))
//    }

}
