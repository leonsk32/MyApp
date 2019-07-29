package leonsk32.myapp.myappback.biz.service

import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import leonsk32.myapp.myappback.biz.service.exception.AlreadyEntriedException
import org.springframework.stereotype.Service

@Service
class HagetakaServiceImpl(
        private val hagetakaRepository: HagetakaRepository
) : HagetakaService {

    override fun entry(name: String, value: Int) {
        if (hagetakaRepository.isEntried(name)) {
            throw AlreadyEntriedException("$name is already entried.")
        }

        hagetakaRepository.save(name, value)
    }

}
