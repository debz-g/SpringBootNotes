package dev.redfox.spring_boot_demo.database.models

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)
