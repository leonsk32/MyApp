package leonsk32.myapp.myappback.biz.service

import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import leonsk32.myapp.myappback.biz.service.exception.AlreadyEntriedException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
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
    internal fun entry() {
        `when`(hagetakaRepository.isEntried("leonsk32"))
                .thenReturn(false)

        hagetakaService.entry("leonsk32", 5)

        verify(hagetakaRepository).save("leonsk32", 5)
    }

    @Test
    @DisplayName("すでに登録されている場合、エラーが返却され登録がされない")
    internal fun alreadyEntried() {
        `when`(hagetakaRepository.isEntried("leonsk32"))
                .thenReturn(true)

        try {
            hagetakaService.entry("leonsk32", 5)
            fail()
        } catch (e: AlreadyEntriedException) {
            assertThat(e.message).isEqualTo("leonsk32 is already entried.")
        }

        verify(hagetakaRepository, never()).save(anyString(), anyInt())
    }
}