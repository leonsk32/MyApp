package leonsk32.myapp.myappback.biz.service

import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class HagetakaServiceImplTests {
    private lateinit var hagetakaService: HagetakaService

    @Mock
    private lateinit var hagetakaRepository: HagetakaRepository

    @BeforeEach
    internal fun setUp() {
        hagetakaService = HagetakaServiceImpl(hagetakaRepository)
    }

    @Test
    @DisplayName("引数で渡された値でリポジトリに登録される")
    internal fun create() {
        hagetakaService.entry("leonsk32", 5)

        verify(hagetakaRepository).save("leonsk32", 5)
    }
}