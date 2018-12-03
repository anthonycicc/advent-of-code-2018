import java.io.FileReader

fun main(args: Array<String>) {
//    val input = FileReader("C:\\Users\\speed\\adventofcode\\src\\main\\resources\\day1.txt")
//    `Day 1 P1 Main`(input.readLines())
//    `Day 1 P2 Main`(input.readLines())

    val input = FileReader("C:\\Users\\speed\\adventofcode\\src\\main\\resources\\day2.txt").readLines()
//    `Day 2 P1 Main`(input)
    `Day 2 P2 Main`(input)

}

fun `Day 1 P1 Main`(input: List<String>) {
    val obj = Day1(input.joinToString("\n"))
    println(obj.calcFinal())
}

fun `Day 1 P2 Main`(input: List<String>) {
    val obj = Day1(input.joinToString("\n"))
    var t = obj.firstReachedTwice()
    while (t == null){
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