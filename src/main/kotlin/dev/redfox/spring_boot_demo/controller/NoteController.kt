package dev.redfox.spring_boot_demo.controller

import dev.redfox.spring_boot_demo.database.models.Note
import dev.redfox.spring_boot_demo.database.models.NoteRequest
import dev.redfox.spring_boot_demo.database.models.NoteResponse
import dev.redfox.spring_boot_demo.database.repository.NoteRepository
import dev.redfox.spring_boot_demo.mappers.toResponse
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(
    private val noteRepository: NoteRepository
) {
    @PostMapping()
    fun save(
        @RequestBody body: NoteRequest
    ): NoteResponse {
        val note = noteRepository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                userId = ObjectId()
            )
        )

        return note.toResponse()
    }

    @GetMapping
    fun findByUserId(
        @RequestParam(required = true) userId: String,
    ): List<NoteResponse> {
        return noteRepository.findByUserId(ObjectId(userId)).map {
            it.toResponse()
        }
    }
}