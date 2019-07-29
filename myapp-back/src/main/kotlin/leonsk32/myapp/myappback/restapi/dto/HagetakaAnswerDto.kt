package leonsk32.myapp.myappback.restapi.dto

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry

class HagetakaAnswerDto(
        val name: String,
        val value: Int
) {
    fun toDomain(): HagetakaEntry {
        return HagetakaEntry(name, value)
    }
}
