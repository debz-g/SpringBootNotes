package dev.redfox.spring_boot_demo.database.repository

import dev.redfox.spring_boot_demo.database.models.RefreshToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface RefreshTokenRepository : MongoRepository<RefreshToken, String> {
    fun findByUserIdAndHashedToken(userId: ObjectId, hashedToken: String): RefreshToken?
    fun deleteBYUserIdAndHashedToken(userId: ObjectId, hashedToken: String)
}