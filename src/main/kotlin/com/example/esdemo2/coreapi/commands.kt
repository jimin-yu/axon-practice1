package com.example.esdemo2.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateRoomCommand(@TargetAggregateIdentifier val roomId: String, val name: String)
data class JoinRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)
data class PostMessageCommand(@TargetAggregateIdentifier val roomId: String, val participant: String, val message: String)
data class LeaveRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)