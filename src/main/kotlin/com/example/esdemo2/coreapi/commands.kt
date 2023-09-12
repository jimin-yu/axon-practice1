package com.example.esdemo2.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

abstract class Command{
    constructor()
    constructor(attributes: Map<String, Any>){
        attributes.forEach {(key, value) ->
            val property = this::class.memberProperties.find { it.name == key }
            if (property is KMutableProperty<*>) {
                property.setter.call(this, value)
            }
        }
    }
}
class CreateRoomCommand : Command{
    @TargetAggregateIdentifier
    var roomId: String = ""
    var name: String = ""

    constructor(attributes: Map<String, Any>) : super(attributes)
    constructor(roomId: String, name: String) : super(){
        this.roomId = roomId
        this.name = name
    }
}
data class JoinRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String) : Command()
data class PostMessageCommand(@TargetAggregateIdentifier val roomId: String, val participant: String, val message: String) : Command()
data class LeaveRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String) : Command()