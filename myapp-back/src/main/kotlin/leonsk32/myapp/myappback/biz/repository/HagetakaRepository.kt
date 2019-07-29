package leonsk32.myapp.myappback.biz.repository

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry

interface HagetakaRepository {
    fun save(hagetakaEntry: HagetakaEntry)
    fun isEntried(hagetakaEntry: HagetakaEntry): Boolean
}
