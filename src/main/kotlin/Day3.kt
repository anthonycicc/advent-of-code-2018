val listOfPoints = mutableListOf<Pair<Int, Int>>()

class Day3(inputList: List<String>) {
    private val inputList = inputList.map { it.toRectangle() }

    fun checkCollisions(): Int {
        var collisions = 0

        for ((index, rect) in inputList.withIndex()) {
            for (oRect in inputList.subList(index, inputList.size)) {
                if (oRect != rect) {
                    collisions += rect.countCollisions(oRect)
                }
            }
        }
        return collisions
    }

    fun checkIntactClaim(): Int {
        val listOfRects = (1..inputList.size).toSet()
        val listOfColliders = mutableSetOf<Int>()
        for ((index, rect) in inputList.withIndex()) {
            for (oRect in inputList.subList(index, inputList.size)) {
                if ((oRect != rect)
                && rect.collides(oRect)) {
                    listOfColliders.add(rect.requestNum)
                    listOfColliders.add(oRect.requestNum)
                }
            }
        }
        val x = listOfRects.minus(listOfColliders)
        return if (x.size == 1) { x.first() } else { 0 }
    }
}

class Rectangle(input: String) {
    val height: Int
    val width: Int
    val upperLeft: Pair<Int, Int>
    val requestNum: Int
    val pointList: List<Pair<Int, Int>>

    init {
        requestNum = input.substring(1, input.indexOf(" ")).toInt()
        upperLeft = Pair(
            input.substringAfter("@ ").substringBefore(",").toInt(),
            input.substringAfter(",").substringBefore(":").toInt()
        )
        width = input.substringAfterLast(" ").substringBefore("x").toInt()
        height = input.substringAfterLast("x").toInt()

        val a = mutableListOf<Pair<Int, Int>>()
        for (x in 0 until width) {
            for (y in 0 until height) {
                a.add(Pair(upperLeft.first + x, upperLeft.second + y))
            }
        }
        pointList = a.toList()
    }

    fun collides(oRect: Rectangle): Boolean {
        for (point in oRect.pointList) {
            if (this.pointInside(point)) {
                return true
            }
        }
        return false
    }

    fun countCollisions(oRect: Rectangle): Int {
        var total = 0
        for (point in oRect.pointList) {
            if (this.pointInside(point)
            && !listOfPoints.contains(point)) {
                total += 1
                listOfPoints.add(point)
            }
        }
        return total
    }

    private fun pointInside(p: Pair<Int, Int>): Boolean{
        return ((p.first.between(this.upperLeft.first, this.upperLeft.first + this.width))
                && (p.second.between(this.upperLeft.second, this.upperLeft.second + this.height)))
    }

    override fun toString(): String {
        return "#$requestNum @ ${upperLeft.first},${upperLeft.second}: ${width}x$height"
    }


}

fun String.toRectangle(): Rectangle {
    return Rectangle(this)
}

fun Int.between(x: Int, y: Int): Boolean {
    return (this >= x) && (this < y)
}