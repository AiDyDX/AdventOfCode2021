import java.lang.Exception

fun main() {
    day01()
    day02()
}

/**
 * Submarine commands for day02_01
 */
class SubmarineTask1(){
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
 * Submarine commands for day02
 */
class SubmarineTask2(){
    var horizontal: Int = 0
    var aim: Int = 0
    var depth: Int = 0

    fun forward(amount: Int = 1){
        this.horizontal += amount
        this.depth += aim * amount
    }
    fun down(amount: Int = 1){
        this.aim += amount
    }
    fun up(amount: Int = 1){
        this.aim -= amount
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
    //task 01
    val sub01 = SubmarineTask1()
    val input = readInput("./data/Day02_01")
    for (command in input){
        sub01.executeMovement(command)
    }
    val endpoint1 = sub01.depth * sub01.horizontal
    println("Distance depth and forward: $endpoint1")
    //task 02
    val sub02 = SubmarineTask2()
    val input02 = readInput("./data/Day02_02")
    for (command in input){
        sub02.executeMovement(command)
    }
    val endpoint2 = sub02.depth * sub02.horizontal
    println("Distance depth and forward corrected: $endpoint2")

}
