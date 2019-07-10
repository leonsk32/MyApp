package leonsk32.myapp.myappback.security

import leonsk32.myapp.myappback.security.Authority.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class Account(
        username: String,
        password: String,
        email: String,
        authority: Authority
) : UserDetails {

    private var username: String = ""
    private var password: String = ""
    private var email: String = ""
    private var authority: Authority = NORMAL_USER

    init{
        this.username = username
        this.password = password
        this.email = email
        this.authority = authority
    }

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