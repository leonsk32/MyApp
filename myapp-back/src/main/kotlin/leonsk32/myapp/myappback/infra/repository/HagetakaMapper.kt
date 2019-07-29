package leonsk32.myapp.myappback.infra.repository

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

@Mapper
interface HagetakaMapper {
    fun save(hagetakaEntry: HagetakaEntry)

    @Results(id = "hagetaka_entries",
            value = [
                Result(column = "id", property = "id"),
                Result(column = "username", property = "username"),
                Result(column = "value", property = "value"),
                Result(column = "created_at", property = "created_at"),
                Result(column = "updated_at", property = "updated_at")
            ])
    @Select("SELECT * FROM hagetaka_entries WHERE username = #{name}")
    fun findByName(name: String): HagetakaEntryEntity?
}
