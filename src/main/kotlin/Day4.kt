import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

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

    fun p1wrapper(): Int {
        val guardAsleepLongest = guardAsleepLongest()
        println("guardAsleepLongest = ${guardAsleepLongest}")
        val h = findHighestFrequencyMinute(guardAsleepLongest)
        println("HighestFrequencyMinute = ${h}")

        return h * guardAsleepLongest
    }

    fun guardAsleepLongest(): Int {
        val guardToTimeAsleep = mutableMapOf<Int, Int>()
        dataList.groupBy { entry: Entry -> entry.guardNum }
            .mapValues { it.value.filter { entry: Entry -> entry.type != NewGuard }}
            .map { Pair(it.key, it.value.partition { it.type == FellAsleep }) }
            .forEach { guardToTimeAsleep.put(it.first, it.second.first.zip(it.second.second)
                    .sumBy { pair -> pair.first.dateTime.until(pair.second.dateTime, ChronoUnit.MINUTES).toInt() })
            }

        println(guardToTimeAsleep)

        return guardToTimeAsleep.maxBy { it.value }?.key ?: 0
    }

    fun findHighestFrequencyMinute(guardNum: Int): Int {
        val minAsleep = mutableMapOf<Int, Int>()
        dataList.filter { entry -> (entry.guardNum == guardNum) && (entry.type != NewGuard) }
            .partition { it.type == FellAsleep }.let {
                val a = it.first.zip(it.second)
                    .map { it -> it.first to it.first.dateTime.until(it.second.dateTime, ChronoUnit.MINUTES) }

                for (timeBetween in a) {
                    for (minute in 0..timeBetween.second) {
                        val entryInQuestion = (timeBetween.first.dateTime.minute + minute).toInt()
                        minAsleep[entryInQuestion] = minAsleep.getOrDefault(entryInQuestion, 0) + 1
                    }
                }
            }

        println(minAsleep)

        return minAsleep.maxBy { it.value }?.key ?: 0
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