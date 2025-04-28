package dev.redfox.spring_boot_demo.database.repository

import dev.redfox.spring_boot_demo.database.models.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note, ObjectId> {
    fun findByUserId(userId: ObjectId): List<Note>
}