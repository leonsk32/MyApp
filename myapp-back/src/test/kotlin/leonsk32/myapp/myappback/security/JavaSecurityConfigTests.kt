package leonsk32.myapp.myappback.security

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class JavaSecurityConfigTests {
    @Autowired
    lateinit var mvc: MockMvc

    private val normalApi = "/api/foo"
    private val adminApi = "/api/admin/hoge"

    @Test
    @DisplayName("ログインしていないユーザが一般APIにアクセスできない")
    fun unauthorizedUserShouldFailedWith401() {
        performGetAndExpectUnauthorized(normalApi)
    }

    @Test
    @DisplayName("ログインしていないユーザが管理者用APIにアクセスできない")
    fun unauthorizedUserShouldFailedWith401AtAdminAPI() {
        performGetAndExpectUnauthorized(adminApi)
    }

    @Test
    @WithMockUser(authorities = arrayOf("ROLE_NORMAL_USER"))
    @DisplayName("一般ユーザが一般APIにアクセスできる")
    fun normalUserShouldSucceedWith200() {
        performGetAndExpectOk(normalApi)
    }

    @Test
    @WithMockUser(authorities = arrayOf("ROLE_NORMAL_USER"))
    @DisplayName("一般ユーザが管理者用APIにアクセスできない")
    fun normalUserShouldFailedWith403AtAdminAPI() {
        performGetAndExpectForbidden(adminApi)
    }

    @WithMockUser(authorities = arrayOf("ROLE_ADMIN"))
    @Test
    @DisplayName("管理者が一般APIにアクセスできる")
    fun adminShouldSucceedWith200() {
        performGetAndExpectOk(normalApi)
    }

    @WithMockUser(authorities = arrayOf("ROLE_ADMIN"))
    @Test
    @DisplayName("管理者が管理者用APIにアクセスできる")
    fun adminShouldSucceedWith200AtAdminAPI() {
        performGetAndExpectOk(adminApi)
    }

    private fun performGetAndExpectOk(url: String) {
        performGetAndExpect(url, status().isOk)
    }

    private fun performGetAndExpectUnauthorized(url: String) {
        performGetAndExpect(url, status().isUnauthorized)
    }

    private fun performGetAndExpectForbidden(url: String) {
        performGetAndExpect(url, status().isForbidden)
    }

    private fun performGetAndExpect(url: String, resultMatcher: ResultMatcher) {
        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(resultMatcher)
    }

}
