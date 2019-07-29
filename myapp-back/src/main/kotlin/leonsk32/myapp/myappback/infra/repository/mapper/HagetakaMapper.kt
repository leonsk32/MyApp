package leonsk32.myapp.myappback.infra.repository.mapper

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.infra.repository.entity.HagetakaEntryEntity
import org.apache.ibatis.annotations.*

@Mapper
interface HagetakaMapper {

    @Update("INSERT INTO hagetaka_entries(username, value, updated_at) VALUES(" +
            "#{name}, " +
            "#{value}, " +
            "current_timestamp)")
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
    fun findByName(@Param("name") name: String): HagetakaEntryEntity?
}
