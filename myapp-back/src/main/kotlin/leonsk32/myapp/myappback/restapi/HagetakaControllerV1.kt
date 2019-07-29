package leonsk32.myapp.myappback.restapi

import leonsk32.myapp.myappback.biz.service.HagetakaService
import leonsk32.myapp.myappback.restapi.dto.HagetakaAnswerDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/hagetaka")
class HagetakaControllerV1(
        private val hagetakaService: HagetakaService
) {
    @PostMapping(consumes = arrayOf("application/json"), path = arrayOf("/entries"))
    @ResponseStatus(HttpStatus.CREATED)
    fun entry(@RequestBody hagetakaAnswerDto: HagetakaAnswerDto) {
        hagetakaService.entry(
                hagetakaAnswerDto.toDomain()
        )
    }
}
