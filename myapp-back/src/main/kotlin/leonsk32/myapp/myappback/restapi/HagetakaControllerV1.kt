package leonsk32.myapp.myappback.restapi

import leonsk32.myapp.myappback.restapi.dto.HagetakaAnswerDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/v1/hagetaka")
class HagetakaControllerV1 {
    @PostMapping(consumes = arrayOf("application/json"))
    fun create(hagetakaAnswerDto: HagetakaAnswerDto) {

    }
}
