package leonsk32.myapp.myappback.infra.repository.mapper

import leonsk32.myapp.myappback.biz.domain.HagetakaEntry
import leonsk32.myapp.myappback.infra.repository.entity.HagetakaEntryEntity
import org.apache.ibatis.annotations.*

@Mapper
interface HagetakaMapper {

    @Update("INSERT INTO hagetaka_entries(round_id, username, value, updated_at) VALUES(" +
            "#{roundId}, " +
            "#{name}, " +
            "#{value}, " +
            "current_timestamp)")
    fun save(hagetakaEntry: HagetakaEntry)

    @Results(id = "hagetaka_entries",
            value = [
                Result(column = "id", property = "id"),
                Result(column = "round_id", property = "roundId"),
                Result(column = "username", property = "username"),
                Result(column = "value", property = "value"),
                Result(column = "created_at", property = "createdAt"),
                Result(column = "updated_at", property = "updatedAt")
            ])
    @Select("SELECT * FROM hagetaka_entries WHERE round_id = #{roundId} AND username = #{name}")
    fun findByRoundIdAndName(@Param("roundId") roundId: Int, @Param("name") name: String): HagetakaEntryEntity?
}
