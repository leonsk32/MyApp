package leonsk32.myapp.myappback.infra.repository

import leonsk32.myapp.myappback.security.Account
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

@Mapper
interface AccountMapper {

    @Results(id = "accounts",
            value = [
                Result(column = "username", property = "username"),
                Result(column = "email", property = "email"),
                Result(column = "authority", property = "authority"),
                Result(column = "password", property = "password")
            ])
    @Select("SELECT * FROM accounts WHERE username = #{username}")
    fun findByUsername(username: String): Account
}
