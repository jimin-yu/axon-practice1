package com.example.esdemo2.config

import com.mongodb.client.MongoClient
import org.axonframework.config.Configurer
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.serialization.json.JacksonSerializer
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonMongoEventStoreConfig {

    @Bean
    fun eventStore(storageEngine: EventStorageEngine, configuration: AxonConfiguration): EmbeddedEventStore {
        return EmbeddedEventStore.builder()
            .storageEngine(storageEngine)
            .messageMonitor(configuration.messageMonitor(EventStore::class.java, "paymenteventStore"))
            .build()
    }

    // The `MongoEventStorageEngine` stores each event in a separate MongoDB document
    @Bean
    fun storageEngine(client: MongoClient): EventStorageEngine {
        return MongoEventStorageEngine.builder()
            .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
            .eventSerializer(JacksonSerializer.defaultSerializer())
            .snapshotSerializer(JacksonSerializer.defaultSerializer())
            .build()
    }
}