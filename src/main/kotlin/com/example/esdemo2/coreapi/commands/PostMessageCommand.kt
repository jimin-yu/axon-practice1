package com.example.esdemo2.coreapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

class PostMessageCommand: Command{
    @TargetAggregateIdentifier
    var roomId: String = ""
    var participant: String = ""
    var message: String = ""

    constructor(attributes: Map<String, Any>) {
        setProperties(attributes)
    }

    constructor(roomId: String, participant: String, message: String) {
        this.roomId = roomId
        this.participant = participant
        this.message = message
    }
}