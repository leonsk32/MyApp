package leonsk32.myapp.myappback.security

import leonsk32.myapp.myappback.security.Authority.NORMAL_USER
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class Account(
        private val username: String = "",
        val email: String = "",
        private val authority: Authority = NORMAL_USER,
        private val password: String = ""
) : UserDetails {



    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = mutableListOf()
        authorities.add(SimpleGrantedAuthority("ROLE_$authority"))
        return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (username != other.username) return false
        if (password != other.password) return false
        if (email != other.email) return false
        if (authority != other.authority) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + authority.hashCode()
        return result
    }

    override fun toString(): String {
        return "Account(username='$username', password='$password', email='$email', authority=$authority)"
    }


}