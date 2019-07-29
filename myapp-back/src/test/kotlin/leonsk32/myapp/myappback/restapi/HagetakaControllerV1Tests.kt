package leonsk32.myapp.myappback.restapi

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class HagetakaControllerV1Tests {
    private lateinit var mvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        mvc = MockMvcBuilders.standaloneSetup(HagetakaControllerV1()).build()
    }

    @Test
    @DisplayName("名前と数字をPOSTするとHTTPステータス200が返る")
    fun name() {
        mvc.perform(
                post("/v1/hagetaka")
                .contentType(APPLICATION_JSON_UTF8)
                // language=json
                .content("{\n  \"name\": \"leonsk32\",\n  \"value\": 5\n}")
        ).andExpect(
                status().isOk
        )

    }
}
