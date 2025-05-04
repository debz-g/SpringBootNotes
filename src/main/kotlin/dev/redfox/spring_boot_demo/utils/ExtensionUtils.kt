package dev.redfox.spring_boot_demo.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.security.MessageDigest
import java.util.*
import javax.crypto.SecretKey

fun String.getRawToken(): String {
    return if (this.startsWith("Bearer")) {
        this.removePrefix("Bearer ")
    } else this
}

fun String.parseAllClaims(secret: SecretKey): Claims? {
    return try {
        Jwts.parser()
            .verifyWith(secret)
            .build()
            .parseSignedClaims(this)
            .payload
    } catch (e: Exception) {
        null
    }
}

fun String.hashToken(): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val hashBytes = digest.digest(this.encodeToByteArray())
    return Base64.getEncoder().encodeToString(hashBytes)
}