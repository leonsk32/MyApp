package leonsk32.myapp.myappback.infra.repository.entity

import java.time.LocalDateTime

class HagetakaEntryEntity(
        var id: Int,
        var username: String,
        var value: Int,
        var created_at: LocalDateTime,
        var updated_at: LocalDateTime
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HagetakaEntryEntity

        if (id != other.id) return false
        if (username != other.username) return false
        if (value != other.value) return false
        if (created_at != other.created_at) return false
        if (updated_at != other.updated_at) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + username.hashCode()
        result = 31 * result + value
        result = 31 * result + created_at.hashCode()
        result = 31 * result + updated_at.hashCode()
        return result
    }

    override fun toString(): String {
        return "HegatakaEntryEntity(id=$id, username='$username', value=$value, created_at=$created_at, updated_at=$updated_at)"
    }
}