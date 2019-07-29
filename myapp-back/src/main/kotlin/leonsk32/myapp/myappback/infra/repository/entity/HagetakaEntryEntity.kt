package leonsk32.myapp.myappback.infra.repository.entity

import java.time.LocalDateTime

data class HagetakaEntryEntity(
        var id: Int,
        var roundId: Int,
        var username: String,
        var value: Int,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime
)