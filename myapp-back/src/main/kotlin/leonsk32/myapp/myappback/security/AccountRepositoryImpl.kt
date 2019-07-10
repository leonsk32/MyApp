package leonsk32.myapp.myappback.security

import leonsk32.myapp.myappback.security.Authority.ADMIN
import leonsk32.myapp.myappback.security.Authority.NORMAL_USER
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl : AccountRepository {
    override fun findByUsername(username: String): Account {

        // 仮実装
        if ("user".equals(username)) {
            return Account(
                    "user",
                    BCryptPasswordEncoder().encode("userPass"),
                    "leonis_sk@example.com",
                    NORMAL_USER

            )
        } else {
            return Account(
                    "admin",
                    BCryptPasswordEncoder().encode("adminPass"),
                    "leonis_sk@example.com",
                    ADMIN
            )
        }
    }
}