package leonsk32.myapp.myappback.security

import leonsk32.myapp.myappback.security.Authority.ADMIN
import leonsk32.myapp.myappback.security.Authority.NORMAL_USER
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
        private val accountMapper: AccountMapper
) : AccountRepository {
    override fun findByUsername(username: String): Account {
        return accountMapper.findByUsername(username)
    }
}