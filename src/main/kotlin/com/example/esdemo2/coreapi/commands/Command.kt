package com.example.esdemo2.coreapi.commands

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

abstract class Command{
//    constructor()

    fun setProperties(attributes: Map<String, Any>){
        attributes.forEach {(key, value) ->
            val property = this::class.memberProperties.find { it.name == key }
            if (property is KMutableProperty<*>) {
                property.setter.call(this, value)
            }
        }
    }
}