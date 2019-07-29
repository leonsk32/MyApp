package leonsk32.myapp.myappback.infra.repository

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import org.springframework.stereotype.Repository

@Repository
class HagetakaRepositoryImpl: HagetakaRepository {
    override fun save(hagetakaEntry: HagetakaEntry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEntried(hagetakaEntry: HagetakaEntry): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}