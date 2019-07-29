package leonsk32.myapp.myappback.biz.service

import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import org.springframework.stereotype.Service

@Service
class HagetakaServiceImpl(
        private val hagetakaRepository: HagetakaRepository
) : HagetakaService {

    override fun entry(name: String, value: Int) {
        hagetakaRepository.save(name, value)
    }

}
