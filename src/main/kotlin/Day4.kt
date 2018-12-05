import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Day4(inputList: List<String>) {
    private val dataList: List<Entry>
    init {
        this.dataList = inputList
            .map { entryString -> entryString.toEntry() }
            .sortedBy { it.dateTime }
            .apply {
                for (date in this.groupBy { it -> it.dateTime.dayOfYear }) {
                    for (entry in date.value) {
                        entry.guardNum = date.value[0].guardNum
                    }
            }
            }
    }

    fun guardAsleepLongest(): Int {
        dataList.forEach { println(it) }
        return 0
    }
}

class Entry(inp: String) {
    val dateTime: LocalDateTime
    val type: EventType
    var guardNum: Int

    init {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        this.dateTime = LocalDateTime.parse(inp.between("[", "]"), dateFormatter)
        this.type = when {
            "falls asleep" in inp -> FellAsleep
            "wakes up" in inp -> Awoke
            else -> NewGuard
        }
        this.guardNum = if (this.type == NewGuard ) { inp.between("#", " ").toInt() } else { 0 }
    }

    override fun toString(): String {
        return "Entry(dateTime=$dateTime, type=$type, guardNum=$guardNum)"
    }

}

sealed class EventType
object NewGuard : EventType()
object FellAsleep : EventType()
object Awoke : EventType()


fun String.toEntry(): Entry {
    return Entry(this)
}
fun String.between(left: String, right: String): String {
    return this.substring(this.indexOf(left) + 1, this.indexOf(right, this.indexOf(left)))
}