package leonsk32.myapp.myappback.security2

interface AccountRepository {
    fun findByUsername(username: String): Account
}