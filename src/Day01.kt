fun main() {
    day01()

}

/**
 * Functions implemented for day one
 */
fun day01() {
    // Gets depth increases and prints the amount
    val input = readInputToInt("./data/Day01")
    val depthIncrease: Int = countDepthIncreases(input)
    println("Depth increases: $depthIncrease")

    // Gets the averaged depth
    val input2 = readInputToInt("./data/Day01_Task2")
    val averagedDepthIncrease: Int = countAddedIncreases(input2)
    println("Added depth increases: $averagedDepthIncrease")
}
