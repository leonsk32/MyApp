package leonsk32.myapp.myappback.restapi.dto

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry

data class HagetakaAnswerDto(
        val name: String,
        val value: Int,
        val roundId: Int
) {
    fun toDomain(): HagetakaEntry {
        return HagetakaEntry(name, value, roundId)
    }
}
