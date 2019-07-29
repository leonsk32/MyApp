package leonsk32.myapp.myappback.infra.repository

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.infra.repository.entity.HagetakaEntryEntity
import leonsk32.myapp.myappback.infra.repository.mapper.HagetakaMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class HagetakaRepositoryImplTests {
    private lateinit var hagetakaRepository: HagetakaRepositoryImpl

    @Mock
    private lateinit var hagetakaMapper: HagetakaMapper

    val notEntriedName = "newUser"
    val entriedName = "knownUser"
    private val notEntried = HagetakaEntry(notEntriedName, 5)
    private val entried = HagetakaEntry(entriedName, 3)

    @BeforeEach
    internal fun setUp() {
        hagetakaRepository = HagetakaRepositoryImpl(hagetakaMapper)
    }

    @Test
    internal fun save() {
        hagetakaRepository.save(notEntried)

        verify(hagetakaMapper).save(notEntried)
    }

    @Nested
    inner class IsEntried {

        @BeforeEach
        internal fun setUp() {
            lenient(). `when`(hagetakaMapper.findByName(notEntriedName)).thenReturn(null)
            lenient(). `when`(hagetakaMapper.findByName(entriedName)).thenReturn(
                    HagetakaEntryEntity(1, entriedName, 5, LocalDateTime.MIN, LocalDateTime.MIN)
            )
        }

        @Test
        @DisplayName("すでに登録されている場合trueを返却する")
        internal fun entried() {
            assertThat(hagetakaRepository.isEntried(entried)).isTrue()
        }

        @Test
        @DisplayName("登録されていない場合falseを返却する")
        internal fun notEntried() {
            assertThat(hagetakaRepository.isEntried(notEntried)).isFalse()
        }

    }
}