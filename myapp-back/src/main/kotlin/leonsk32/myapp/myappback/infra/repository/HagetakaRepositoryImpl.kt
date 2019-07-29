package leonsk32.myapp.myappback.infra.repository

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import leonsk32.myapp.myappback.infra.repository.mapper.HagetakaMapper
import org.springframework.stereotype.Repository
import java.util.Objects.isNull

@Repository
class HagetakaRepositoryImpl(
        private val hagetakaMapper: HagetakaMapper
) : HagetakaRepository {

    override fun save(hagetakaEntry: HagetakaEntry) {
        hagetakaMapper.save(hagetakaEntry)
    }

    override fun isEntried(hagetakaEntry: HagetakaEntry): Boolean {
        val searchedEntry = hagetakaMapper.findByName(hagetakaEntry.name)

        return !isNull(searchedEntry)
    }
}