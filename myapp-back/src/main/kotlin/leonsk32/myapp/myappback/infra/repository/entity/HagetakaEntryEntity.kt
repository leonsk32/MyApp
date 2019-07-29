package leonsk32.myapp.myappback.infra.repository.entity

import java.time.LocalDateTime

class HagetakaEntryEntity(
        var id: Int,
        var roundId: Int,
        var username: String,
        var value: Int,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HagetakaEntryEntity

        if (id != other.id) return false
        if (roundId != other.roundId) return false
        if (username != other.username) return false
        if (value != other.value) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + roundId
        result = 31 * result + username.hashCode()
        result = 31 * result + value
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }

    override fun toString(): String {
        return "HagetakaEntryEntity(id=$id, roundId=$roundId, username='$username', value=$value, created_at=$createdAt, updated_at=$updatedAt)"
    }
}