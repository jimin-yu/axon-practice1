package com.example.esdemo2.consumer

import com.example.esdemo2.consumer.dto.CommandMessage
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.axonframework.commandhandling.gateway.CommandGateway
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class KafkaConsumer(
    private val objectMapper: ObjectMapper,
    private val commandGateway: CommandGateway) {
    class InvalidCommandNameException(message: String) : Exception(message)

    companion object {
        const val PACKAGE_NAME = "com.example.esdemo2.coreapi"
    }
    val commandClasses = findCommandClasses(PACKAGE_NAME)

    @KafkaListener(topics = ["event-sourcing"], groupId = "my-consumer-group")
    fun listen(message: String) {
        val kafkaMessage = objectMapper.readValue(message, CommandMessage::class.java)
        // Kafka 메시지를 처리하는 로직을 작성
        println("Received Kafka message: ${kafkaMessage}")
        val command = createCommand(kafkaMessage.commandName, kafkaMessage.body)
        commandGateway.sendAndWait<Any>(command)
    }

    private fun createCommand(commandName: String, body: Map<String, Any>) : Any {
        if(!commandClasses.contains(commandName)){
            throw InvalidCommandNameException("invalid class!!!!")
        }
        val fullClassName = "${PACKAGE_NAME}.${commandName}"
        val loadedClass = Class.forName(fullClassName)
        val constructor = loadedClass.getDeclaredConstructor(Map::class.java)

        return constructor.newInstance(body)
    }

    private fun findCommandClasses(packageName: String?): Set<String> {
        val reflections = Reflections(packageName, SubTypesScanner(false))
        val commandInterface = Class.forName("com.example.esdemo2.coreapi.Command")
        return reflections.getSubTypesOf(commandInterface)
            .map { it.simpleName }
            .toSet()
    }
}