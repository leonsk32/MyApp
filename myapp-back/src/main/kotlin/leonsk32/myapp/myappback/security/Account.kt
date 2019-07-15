package leonsk32.myapp.myappback.security

import leonsk32.myapp.myappback.security.Authority.NORMAL_USER
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class Account(
        private val username: String = "",
        private val password: String = "",
        val email: String = "",
        private val authority: Authority = NORMAL_USER
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

}