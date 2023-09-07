package com.example.esdemo2.restapi

import com.example.esdemo2.query.messages.ChatMessage
import com.example.esdemo2.query.messages.ChatMessageRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ReadController(private val chatMessageRepository: ChatMessageRepository) {
    @GetMapping("/hello")
    fun sayHello(): String {
        val chatMessage = ChatMessage("room1", "jane", "hello~~", 0)
        chatMessageRepository.save(chatMessage)
        return "Hello, World!"
    }
}