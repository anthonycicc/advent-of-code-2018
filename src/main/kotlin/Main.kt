import java.io.File

fun main(args: Array<String>) {
    val reader = object {}::class.java.classLoader
//    val inputDay1 = reader.getResource("day1.txt").readText().split("\n")
//    `Day 1 P1 Main`(inputDay1)
//    `Day 1 P2 Main`(inputDay1)

//    val inputDay2 = reader.getResource("day2.txt").readText().split("\n")
//    `Day 2 P1 Main`(inputDay2)
//    `Day 2 P2 Main`(inputDay2)

//    val inputDay3 = reader.getResource("day3.txt").readText().split("\n")
//    `Day 3 P1 Main`(inputDay3)
//    `Day 3 P2 Main`(inputDay3)

    val inputDay4 = reader.getResource("day4example.txt").readText().split("\n")
    `Day 4 P1 Main`(inputDay4)
}

fun `Day 1 P1 Main`(input: List<String>) {
    val obj = Day1(input.joinToString("\n"))
    println(obj.calcFinal())
}

fun `Day 1 P2 Main`(input: List<String>) {
    val obj = Day1(input.joinToString("\n"))
    var t = obj.firstReachedTwice()
    while (t == null) {
//        println(t)
        t = obj.firstReachedTwice()
    }
    println(t)
}

fun `Day 2 P1 Main`(input: List<String>) {
    val obj = Day2(input)

    println(obj.calcChecksum())
}

fun `Day 2 P2 Main`(input: List<String>) {
    val obj = Day2(input)

    println(obj.checkDiffs())
}

fun `Day 3 P1 Main`(input: List<String>) {
    val obj = Day3(input)

    println(obj.checkCollisions())
}

fun `Day 3 P2 Main`(input: List<String>): Unit {
    val obj = Day3(input)
    println(obj.checkIntactClaim())
}

fun `Day 4 P1 Main`(input: List<String>): Unit {
    val obj = Day4(input)
    println(obj.guardAsleepLongest())

}