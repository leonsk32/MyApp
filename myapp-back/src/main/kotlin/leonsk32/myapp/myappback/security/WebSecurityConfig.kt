package leonsk32.myapp.myappback.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
                "/img/**",
                "/css/**",
                "/js/**",
                "/webjars/**"
        )
    }

    override fun configure(http: HttpSecurity) {
        // 許可の設定
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll() // indexは全ユーザーアクセス許可
//                .antMatchers("/system").hasRole(ADMIN.toString())
                .anyRequest().authenticated()  // それ以外は全て認証無しの場合アクセス不許可

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/users/login") // 認証処理のパス
                .loginPage("/index")   // ログインフォームのパス
//                .failureHandler(AuthenticationFailureHandler()) // 認証失敗時に呼ばれるハンドラクラス
                .defaultSuccessUrl("/login/success") // 認証成功時の遷移先
                .usernameParameter("email").passwordParameter("password") // ユーザー名、パスワードのパラメータ名
                .and()

        // ログアウト
        http.logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/index")
    }

    @Configuration
    class AuthenticationConfiguration(
            private val userDetailsService: UserDetailsService
    ) : GlobalAuthenticationConfigurerAdapter() {
        @Bean
        fun passwordEncoder(): PasswordEncoder {
            return NoOpPasswordEncoder.getInstance()
        }

        override fun init(auth: AuthenticationManagerBuilder) {
            auth.userDetailsService(userDetailsService)
        }
    }
}