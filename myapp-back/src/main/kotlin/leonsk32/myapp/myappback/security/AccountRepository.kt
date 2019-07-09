package leonsk32.myapp.myappback.security

import leonsk32.myapp.myappback.security.Account

interface AccountRepository {
    fun findByUsername(username: String): Account
}