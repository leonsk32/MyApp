package leonsk32.myapp.myappback.restapi

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.biz.service.HagetakaService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@ExtendWith(MockitoExtension::class)
class HagetakaControllerV1Tests {
    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var hagetakaService: HagetakaService

    private val aName = "leonsk32"
    private val aValue = 5
    private val aRoundId = 103
    private val aRequest = "{\"name\": \"$aName\",  \"value\": $aValue, \"roundId\": $aRoundId}"

    @BeforeEach
    internal fun setUp() {
        mvc = standaloneSetup(HagetakaControllerV1(hagetakaService)).build()
    }

    @Nested
    inner class Entry {
        @Test
        @DisplayName("HTTPステータス201が返る")
        internal fun returns200() {
            perfomPost(aRequest).andExpect(status().isCreated)
        }

        @Test
        @DisplayName("サービスを適切な引数でコールする")
        internal fun callsServiceWithArgs() {
            perfomPost(aRequest)
            verify(hagetakaService).entry(HagetakaEntry(aName, aValue, aRoundId))
        }

        private fun perfomPost(request: String): ResultActions {
            return mvc.perform(
                    post("/v1/hagetaka/entries")
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(request)
            )
        }
    }

    @Nested
    inner class FindByRoundId {
        @Test
        @DisplayName("HTTPステータス200が返る")
        internal fun returns200() {
            performGet(95).andExpect(status().isOk)
        }


        private fun performGet(roundId: Int): ResultActions {
            return mvc.perform(
                    get("/v1/hagetaka/entries?roundId=$roundId")
            )
        }
    }
}
