package leonsk32.myapp.myappback.security2

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Objects.isNull

//@Service
class AccountService(
        private val accountRepository: AccountRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        if (isNull(username) || username.isEmpty()) {
            throw UsernameNotFoundException("")
        }

        val account = accountRepository.findByUsername(username)
        if (isNull(account)) {
            throw UsernameNotFoundException("")
        }

        return account
    }

}