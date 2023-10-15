package com.example.esdemo2.query.summary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomSummaryRepository : JpaRepository<RoomSummary, String> {
}