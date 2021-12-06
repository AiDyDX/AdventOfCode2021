fun main() {
    fun part1(input: List<Int>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputToInt("Day01_test")
    check(part1(testInput) == 10)

    // Gets depth increases and prints the amount
    val input = readInputToInt("Day01")
    val depthIncrease: Int = countDepthIncreases(input)
    println(depthIncrease)

}
