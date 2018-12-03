import java.io.FileReader

fun main(args: Array<String>) {
    val input = FileReader("C:\\Users\\speed\\adventofcode\\src\\main\\resources\\day1.1.txt")
//    `Day 1 P1 Main`(input.readLines())
    `Day 1 P2 Main`(input.readLines())

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