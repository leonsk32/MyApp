package leonsk32.myapp.myappback.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler


@Configuration
class JavaSecurityConfig(
        private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint
) : WebSecurityConfigurerAdapter() {
    private val simpleUrlAuthenticationFailureHandler = SimpleUrlAuthenticationFailureHandler()
    private val mySavedRequestAwareAuthenticationSuccessHandler = MySavedRequestAwareAuthenticationSuccessHandler()

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/foo").authenticated()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .successHandler(mySavedRequestAwareAuthenticationSuccessHandler)
                .failureHandler(simpleUrlAuthenticationFailureHandler)
                .and()
                .logout()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
                .and()
                .withUser("user").password(encoder().encode("userPass")).roles("USER")
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}