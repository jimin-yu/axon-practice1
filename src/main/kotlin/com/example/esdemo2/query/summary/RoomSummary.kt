package com.example.esdemo2.query.summary

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RoomSummary {
    @Id
    var roomId: String? = null
    var name: String = ""
    var participants: Int = 0
    constructor()
    constructor(roomId: String, name: String) {
        this.roomId = roomId
        this.name = name
    }
}