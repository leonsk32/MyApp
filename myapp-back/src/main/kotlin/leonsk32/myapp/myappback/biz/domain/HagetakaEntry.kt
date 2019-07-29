package leonsk32.myapp.myappback.biz.domain

class HagetakaEntry(
        val name: String,
        val value: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HagetakaEntry

        if (name != other.name) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + value
        return result
    }
}
