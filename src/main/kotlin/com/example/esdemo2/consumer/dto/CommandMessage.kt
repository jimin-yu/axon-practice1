package com.example.esdemo2.consumer.dto

data class CommandMessage(val commandName: String, val body: Map<String, Any>)