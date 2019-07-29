package leonsk32.myapp.myappback.infra.repository.mapper

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT
import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.infra.repository.entity.HagetakaEntryEntity
import leonsk32.myapp.myappback.infra.repository.mapper.HagetakaMapper
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
    inner class Save {

        @Test
        @DatabaseSetup("/dbunit/accounts.xml")
        @ExpectedDatabase(value = "/dbunit/hagetaka_entries_after.xml", table = "hagetaka_entries", assertionMode = NON_STRICT)
        internal fun save() {
            val hagetakaEntry = HagetakaEntry("user", 5)
            hagetakaMapper.save(hagetakaEntry)
        }

    }


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