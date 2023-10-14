package com.example.esdemo2.coreapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

class CreateRoomCommand : Command{
    @TargetAggregateIdentifier
    var roomId: String = ""
    var name: String = ""

    constructor(attributes: Map<String, Any>) {
        this.roomId = UUID.randomUUID().toString()
        setProperties(attributes)
    }
    constructor(roomId: String, name: String) {
        this.roomId = roomId
        this.name = name
    }
}