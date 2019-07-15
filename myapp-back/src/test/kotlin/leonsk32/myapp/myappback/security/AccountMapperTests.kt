package leonsk32.myapp.myappback.security

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import leonsk32.myapp.myappback.security.Authority.ADMIN
import leonsk32.myapp.myappback.security.Authority.NORMAL_USER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener

@MybatisTest
@TestExecutionListeners(
        DependencyInjectionTestExecutionListener::class,
        DirtiesContextTestExecutionListener::class,
        TransactionDbUnitTestExecutionListener::class
)
class AccountMapperTests {

    @Autowired
    lateinit var accountMapper: AccountMapper

    val normalUser = Account(
            "user",
            "user@example.com",
            NORMAL_USER,
            "encoded_user_password"
    )

    val admin = Account(
            "admin",
            "admin@example.com",
            ADMIN,
            "encoded_admin_password"
    )

    @Test
    @DatabaseSetup("/dbunit/accounts.xml")
    @DisplayName("ユーザ名で検索できる")
    internal fun happyPath() {
        val actualNormalUser = accountMapper.findByUsername(normalUser.username)
        val actualAdmin = accountMapper.findByUsername(admin.username)

        assertThat(actualNormalUser).isEqualTo(normalUser)
        assertThat(actualAdmin).isEqualTo(admin)
    }

    @Test
    @DisplayName("存在しないユーザ名で検索すると Null が取得される")
    internal fun noExistingUser() {
        val actual = accountMapper.findByUsername("noExistingName")

        assertThat(actual).isNull()
    }

}