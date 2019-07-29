package leonsk32.myapp.myappback.restapi

import leonsk32.myapp.myappback.biz.service.HagetakaService
import leonsk32.myapp.myappback.restapi.dto.HagetakaAnswerDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/v1/hagetaka")
class HagetakaControllerV1(
        private val hagetakaService: HagetakaService
) {
    @PostMapping(consumes = arrayOf("application/json"))
    fun create(@RequestBody hagetakaAnswerDto: HagetakaAnswerDto) {
        hagetakaService.entry(
                hagetakaAnswerDto.name,
                hagetakaAnswerDto.value
        )
    }
}
