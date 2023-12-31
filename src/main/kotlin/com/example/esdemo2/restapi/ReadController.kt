package com.example.esdemo2.restapi

import com.example.esdemo2.coreapi.AllRoomsQuery
import com.example.esdemo2.coreapi.RoomMessagesQuery
import com.example.esdemo2.coreapi.RoomParticipantsQuery
import com.example.esdemo2.coreapi.commands.CreateRoomCommand
import com.example.esdemo2.coreapi.commands.JoinRoomCommand
import com.example.esdemo2.query.messages.ChatMessage
import com.example.esdemo2.query.messages.ChatMessageRepository
import com.example.esdemo2.query.participants.RoomParticipant
import com.example.esdemo2.query.summary.RoomSummary
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/rooms")
class ReadController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway)
{

    @GetMapping("all")
    fun listRooms(): CompletableFuture<List<RoomSummary>> {
        return queryGateway.query(
            AllRoomsQuery(),
            MultipleInstancesResponseType(RoomSummary::class.java)
        )
    }

    @GetMapping("{roomId}/participants")
    fun participantsInRoom(@PathVariable roomId: String): CompletableFuture<List<RoomParticipant>> {
        return queryGateway.query(
            RoomParticipantsQuery(roomId),
            MultipleInstancesResponseType(RoomParticipant::class.java)
        )
    }

    @GetMapping("{roomId}/messages")
    fun roomMessages(@PathVariable roomId: String): CompletableFuture<List<ChatMessage>> {
        return queryGateway.query(
            RoomMessagesQuery(roomId),
            MultipleInstancesResponseType(ChatMessage::class.java)
        )
    }

    @PostMapping
    fun createChatRoom(@RequestBody createRoomReqeust: CreateRoomReqeust): String {
        val roomId = UUID.randomUUID().toString()
        val command = CreateRoomCommand(roomId, createRoomReqeust.name)
        commandGateway.sendAndWait<Any>(command)
        return "ok"
    }
}