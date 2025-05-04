package dev.redfox.spring_boot_demo.security

import dev.redfox.spring_boot_demo.database.models.TokenPair
import dev.redfox.spring_boot_demo.database.models.User
import dev.redfox.spring_boot_demo.database.repository.RefreshTokenRepository
import dev.redfox.spring_boot_demo.database.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val hashedEncoder: HashedEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun register(email: String, password: String): User =
        User(email = email, hashedPassword = hashedEncoder.encode(password))

    fun login(email: String, password: String): TokenPair {
        val user = userRepository.findByEmail(email) ?: throw BadCredentialsException("Invalid Credentials.")

        if (!hashedEncoder.matches(password, user.hashedPassword)) {
            throw BadCredentialsException("Invalid Credentials.")
        }

        val newAccessToken = jwtService.generateAccessToken(user.id.toHexString())
        val newRefreshToken = jwtService.generateRefreshToken(user.id.toHexString())

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    private fun storeRefreshToken(userId: ObjectId, rawRefreshToken: String) {

    }
}