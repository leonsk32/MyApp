package leonsk32.myapp.myappback.security;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Results(id = "accounts",
            value = {
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "authority", property = "authority")
            })
    @Select({"SELECT * FROM accounts WHERE username = #{username}"})
    Account findByUsername(String username);
}

