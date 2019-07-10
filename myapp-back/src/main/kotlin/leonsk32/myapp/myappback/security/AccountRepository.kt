package leonsk32.myapp.myappback.security

interface AccountRepository {
    fun findByUsername(username: String): Account
}