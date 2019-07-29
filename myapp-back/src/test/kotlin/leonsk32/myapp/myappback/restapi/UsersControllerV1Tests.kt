package leonsk32.myapp.myappback.restapi

import leonsk32.myapp.myappback.biz.service.HagetakaService
import leonsk32.myapp.myappback.biz.service.UsersService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class UsersControllerV1Tests {
    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var usersService: UsersService

    @BeforeEach
    internal fun setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(HagetakaControllerV1(usersService)).build()
    }
}