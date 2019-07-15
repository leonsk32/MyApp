package leonsk32.myapp.myappback.security

import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
        private val accountMapper: AccountMapper
) : AccountRepository {
    override fun findByUsername(username: String): Account {
        return accountMapper.findByUsername(username)
    }
}