package dev.redfox.spring_boot_demo.database.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("notes")
data class Note(
    val title: String,
    val content: String,
    val color: Long,
    val createdAt: Instant,
    val userId: ObjectId,
    @Id val id: ObjectId = ObjectId.get()
)

data class NoteRequest(
    val id: String?,
    val title: String,
    val content: String,
    val color: Long
)

data class NoteResponse(
    val id: String,
    val title: String,
    val content: String,
    val color: Long,
    val createdAt: Instant
)