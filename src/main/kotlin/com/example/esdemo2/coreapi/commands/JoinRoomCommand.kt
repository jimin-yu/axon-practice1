package com.example.esdemo2.coreapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class JoinRoomCommand: Command{
    @TargetAggregateIdentifier
    var roomId: String = ""
    var participant: String = ""

    constructor(attributes: Map<String, Any>) {
        setProperties(attributes)
    }

    constructor(roomId: String, participant: String) {
        this.roomId = roomId
        this.participant = participant
    }
}