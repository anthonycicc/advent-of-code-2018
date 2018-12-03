class Day2(inputList: List<String>) {
    val inputList: List<String> = inputList

    constructor(inputString: String) : this(inputList = inputString.split(',').map { it.trim() })

    fun calcChecksum(): Int {
        var twos = 0
        var threes = 0

        for (word in inputList) {
            val t = mutableMapOf<Char, Int>()
            for (char in word) {
                t[char] = t.getOrDefault(char, 0) + 1
            }

            var twoFlag = false
            var threeFlag = false
            for (entry in t) {
                when (entry.value) {
                    2 -> twoFlag = true
                    3 -> threeFlag = true
                }
            }
            if (twoFlag) {
                twos += 1
            }
            if (threeFlag) {
                threes += 1
            }
        }
        return twos * threes
    }

    fun checkDiffs(): String {
        var firstLine = ""
        var secondLine = ""

        for ((index, line) in inputList.withIndex()) {
            for (oLine in inputList.subList(index, inputList.size - 1)) {
                if ((line != oLine) && (line.diff(oLine) < 2)) {
                    firstLine = line
                    secondLine = oLine
                }
            }
        }

        var totalLineMinusDups = ""
        for (i in 0 until firstLine.length){
            if (firstLine[i] == secondLine[i]) {
                totalLineMinusDups += firstLine[i]
            }
        }
        return totalLineMinusDups
    }
}

fun String.diff(oString: String): Int {
    assert(this.length == oString.length)
    var difference = 0
    for (charIndex in 0 until this.length) {
        if (this[charIndex] != oString[charIndex]){
            difference += 1
        }
    }
    return difference
}