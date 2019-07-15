package leonsk32.myapp.myappback.biz.repository

import leonsk32.myapp.myappback.security.Account

interface AccountRepository {
    fun findByUsername(username: String): Account
}