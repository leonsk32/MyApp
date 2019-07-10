package leonsk32.myapp.myappback.security2

import leonsk32.myapp.myappback.security2.Authority.NORMAL_USER
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl : AccountRepository {
    override fun findByUsername(username: String): Account {

        // 仮
        return Account(
                "leonis_sk",
                "hogehoge",
                "leonis_sk@example.com",
                NORMAL_USER

        )
    }
}