package com.example.esdemo2.restapi

import com.example.esdemo2.coreapi.commands.CreateRoomCommand
import com.example.esdemo2.coreapi.commands.JoinRoomCommand
import com.example.esdemo2.query.messages.ChatMessage
import com.example.esdemo2.query.messages.ChatMessageRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/rooms")
class ReadController(private val commandGateway: CommandGateway) {
    @GetMapping("/hello")
    fun sayHello(): String {
        val chatMessage = ChatMessage("room1", "jane", "hello~~", 0)
//        chatMessageRepository.save(chatMessage)
        return "Hello, World!"
    }

    @PostMapping
    fun createChatRoom(@RequestBody createRoomReqeust: CreateRoomReqeust): String {
//        val roomId = UUID.randomUUID().toString()
        val command = JoinRoomCommand("e1ce4351-8b70-464a-9f60-d41b59d93bd0", "Danny Koo")
        System.out.println("command" + command)
        commandGateway.sendAndWait<Any>(command)
        return "ok"
    }
}