package dev.redfox.spring_boot_demo.mappers

import dev.redfox.spring_boot_demo.database.models.Note
import dev.redfox.spring_boot_demo.database.models.NoteResponse

fun Note.toResponse(): NoteResponse {
    return NoteResponse(
        id = id.toHexString(),
        title = title,
        content = content,
        color = color,
        createdAt = createdAt,
    )
}