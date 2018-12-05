import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Day4(inputList: List<String>) {
    private val dataList: List<Entry>
    init {
        val tempList = mutableListOf<Entry>()
        var guardNum = 0

        for (entry in inputList.sorted()) {
            if (entry.find { it == '#' } != null) {
                guardNum = entry.between("#", " ").toInt()
            }
            tempList.add(Entry(entry, guardNum))
        }
        this.dataList = tempList.toList()
    }

    fun guardAsleepLongest(): Int {
        dataList.forEach { println(it) }
        return 0
    }
}

class Entry(inp: String, val guardNum: Int) {
    val dateTime: LocalDateTime
    val type: EventType

    init {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        this.dateTime = LocalDateTime.parse(inp.between("[", "]"), dateFormatter)
        this.type = when {
            "falls asleep" in inp -> FellAsleep
            "wakes up" in inp -> Awoke
            else -> NewGuard
        }
    }

    override fun toString(): String {
        return "Entry(dateTime=$dateTime, type=$type, guardNum=$guardNum)"
    }

}

sealed class EventType
object NewGuard : EventType()
object FellAsleep : EventType()
object Awoke : EventType()

fun String.between(left: String, right: String): String {
    return this.substring(this.indexOf(left) + 1, this.indexOf(right, this.indexOf(left)))
}