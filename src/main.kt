import java.lang.Exception

fun main() {
    day01()
    day02()
}

/**
 * Submarine commands for day02
 */
class Submarine(){
    var horizontal: Int = 0
    var depth: Int = 0
    fun forward(amount: Int = 1){
        this.horizontal += amount
    }

    fun down(amount: Int = 1){
        this.depth += amount
    }
    fun up(amount: Int = 1){
        this.depth -= amount
    }
    fun executeMovement(command: String){
        val split_command = command.split(" ")
        val direction: String = split_command[0]
        val distance: Int = split_command[1].toInt()
        when (direction) {
            "forward" -> this.forward(distance)
            "down" -> this.down(distance)
            "up" -> this.up(distance)
            else -> throw Exception("Incorrect movement detected")
        }
    }
}

/**
 * Functions implemented for day one
 */
fun day01() {
    // Gets depth increases and prints the amount
    val input = readInputToInt("./data/Day01_01")
    val depthIncrease: Int = countDepthIncreases(input)
    println("Depth increases: $depthIncrease")

    // Gets the averaged depth
    val input2 = readInputToInt("./data/Day01_02")
    val averagedDepthIncrease: Int = countAddedIncreases(input2)
    println("Added depth increases: $averagedDepthIncrease")
}

/**
 * Functions implemented for day02
 */
fun day02() {
    var sub = Submarine()
    val input = readInput("./data/Day02_01")
    for (command in input){
        sub.executeMovement(command)
    }
    val movement = sub.depth * sub.horizontal
    println("Distance depth and forward: $movement")
}
