package dev.redfox.spring_boot_demo.database.repository

import dev.redfox.spring_boot_demo.database.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
}