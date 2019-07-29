package leonsk32.myapp.myappback.biz.service

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.biz.repository.HagetakaRepository
import leonsk32.myapp.myappback.biz.service.exception.AlreadyEntriedException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class HagetakaServiceImplTests {
    private lateinit var hagetakaService: HagetakaService

    @Mock
    private lateinit var hagetakaRepository: HagetakaRepository

    private val newEntry = HagetakaEntry("newEntry", 5, 100)
    private val entriedName = "entried"
    private val entried = HagetakaEntry(entriedName, 3, 100)

    @BeforeEach
    internal fun setUp() {
        hagetakaService = HagetakaServiceImpl(hagetakaRepository)
        lenient().`when`(hagetakaRepository.isEntried(newEntry)).thenReturn(false)
        lenient().`when`(hagetakaRepository.isEntried(entried)).thenReturn(true)
    }

    @Test
    @DisplayName("新しいエントリがリポジトリに登録される")
    internal fun entry() {
        hagetakaService.entry(newEntry)

        verify(hagetakaRepository).save(newEntry)
    }

    @Test
    @DisplayName("すでに登録されている場合、エラーが返却され登録がされない")
    internal fun alreadyEntried() {
        try {
            hagetakaService.entry(entried)
            fail()
        } catch (e: AlreadyEntriedException) {
            assertThat(e.message).isEqualTo("\"$entriedName\" is already entried.")
        }

        verify(hagetakaRepository, never()).save(any(HagetakaEntry::class.java))
    }

    private fun <T> any(clazz: Class<T>): T {
        return Mockito.any(clazz)
    }
}