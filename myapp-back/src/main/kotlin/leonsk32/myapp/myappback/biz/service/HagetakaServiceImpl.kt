package leonsk32.myapp.myappback.biz.service

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import leonsk32.myapp.myappback.biz.service.exception.AlreadyEntriedException
import org.springframework.stereotype.Service

@Service
class HagetakaServiceImpl(
        private val hagetakaRepository: HagetakaRepository
) : HagetakaService {

    override fun entry(hagetakaEntry: HagetakaEntry) {
        if (hagetakaRepository.isEntried(hagetakaEntry)) {
            throw AlreadyEntriedException("\"${hagetakaEntry.name}\" is already entried.")
        }

        hagetakaRepository.save(hagetakaEntry)
    }
}
