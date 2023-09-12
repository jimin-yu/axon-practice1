package com.example.esdemo2.consumer

import com.example.esdemo2.coreapi.Message
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.stream.Collectors





@Component
@RequiredArgsConstructor
class KafkaConsumer(private val objectMapper: ObjectMapper) {

    companion object {
        const val PACKAGE_NAME = "com.example.esdemo2.coreapi"
    }

    @KafkaListener(topics = ["event-sourcing"], groupId = "my-consumer-group")
    fun listen(message: String) {
        val hello = objectMapper.readValue(message, Message::class.java)
        // Kafka 메시지를 처리하는 로직을 작성
        println("Received message: ${hello.text}")
    }

//    fun getCommandClassNames(){
//        val classes = getClassesInPackage(packageName)
//    }
//
//    private fun findAllClassesUsingReflectionsLibrary(packageName: String?): Set<Class<*>> {
//        val reflections = Reflections(packageName, SubTypesScanner(false))
//        return reflections.getSubTypesOf(Any::class.java)
//            .stream()
//            .collect(Collectors.toSet())
//    }
}