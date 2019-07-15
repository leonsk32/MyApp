package leonsk32.myapp.myappback.infra.repository

import leonsk32.myapp.myappback.security.Account
import leonsk32.myapp.myappback.biz.repository.AccountRepository
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
        private val accountMapper: AccountMapper
) : AccountRepository {
    override fun findByUsername(username: String): Account {
        return accountMapper.findByUsername(username)
    }
}