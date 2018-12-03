class Day3(inputList: List<String>) {
    private val inputList = inputList.map { it.toRectangle() }

    fun checkCollisions(): Int {
        var collisions = 0

        for ((index, rect) in inputList.withIndex()) {
            for (oRect in inputList.subList(index, inputList.size - 1)) {
                if ((rect != oRect) && (rect.collides(oRect))) {
                    collisions += rect.countCollisions(oRect)
                }
            }
        }

        return collisions
    }
}

class Rectangle(input: String) {
    val height : Int
    val width : Int
    val upperLeft: Pair<Int, Int>
    val requestNum: Int

    init {
        requestNum = input.substring(1, input.indexOf(" ")).toInt()
        upperLeft = Pair(
            input.substringAfter("@ ").substringBefore(",").toInt(),
            input.substringAfter(",").substringBefore(":").toInt())
        width = input.substringAfterLast(" ").substringBefore("x").toInt()
        height = input.substringAfterLast("x").toInt()
    }

    fun collides(oRect: Rectangle): Boolean {
        return true
    }

    fun countCollisions(oRect: Rectangle): Int {
        return 0
    }

    override fun toString(): String {
        return "#$requestNum @ ${upperLeft.first},${upperLeft.second}: ${width}x$height"
    }
}

fun String.toRectangle(): Rectangle{
    return Rectangle(this)
}