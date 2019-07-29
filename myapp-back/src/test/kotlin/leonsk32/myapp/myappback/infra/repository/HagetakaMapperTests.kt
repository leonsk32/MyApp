package leonsk32.myapp.myappback.infra.repository

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import java.time.LocalDateTime

@MybatisTest
class HagetakaMapperTests {

    @Autowired
    lateinit var hagetakaMapper: HagetakaMapper

    @Nested
    @MybatisTest
    @TestExecutionListeners(
            DependencyInjectionTestExecutionListener::class,
            DirtiesContextTestExecutionListener::class,
            TransactionDbUnitTestExecutionListener::class
    )
    inner class FindByName {
        @Test
        @DatabaseSetup("/dbunit/accounts.xml", "/dbunit/hagetaka_entries.xml")
        @DisplayName("該当レコードが存在する場合正しく取得できる")
        internal fun exists() {
            val expected = HagetakaEntryEntity(
                    1,
                    "user",
                    5,
                    LocalDateTime.of(2018, 1, 1, 10, 10, 10),
                    LocalDateTime.of(2018, 12, 31, 1, 1, 1)
            )
            val actual = hagetakaMapper.findByName("user")

            assertThat(actual).isEqualTo(expected)
        }

        @Test
        @DisplayName("該当レコードが存在しない場合 Null を返却する")
        internal fun notExists() {
            val actual = hagetakaMapper.findByName("notExistUser")

            assertThat(actual).isNull()
        }

    }
}