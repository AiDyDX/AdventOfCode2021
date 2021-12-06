import java.lang.Exception
import kotlin.math.pow

fun main() {
    day01()
    day02()
    day03()
}

/**
 * Submarine commands for day02_01
 */
class SubmarineTask1{
    var horizontal: Int = 0
    var depth: Int = 0

    private fun forward(amount: Int = 1){
        this.horizontal += amount
    }
    private fun down(amount: Int = 1){
        this.depth += amount
    }
    private fun up(amount: Int = 1){
        this.depth -= amount
    }
    fun executeMovement(command: String){
        val splitCommand = command.split(" ")
        val direction: String = splitCommand[0]
        val distance: Int = splitCommand[1].toInt()
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
class SubmarineCorrected{
    var horizontal: Int = 0
    private var aim: Int = 0
    var depth: Int = 0
    var batteryPower: Int = 0

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
        val splitCommand = command.split(" ")
        val direction: String = splitCommand[0]
        val distance: Int = splitCommand[1].toInt()
        when (direction) {
            "forward" -> this.forward(distance)
            "down" -> this.down(distance)
            "up" -> this.up(distance)
            else -> throw Exception("Incorrect movement detected")
        }
    }
    fun calculatePower(data: List<Boolean>){
        var gammaRate = 0.0f
        var epsilonRate = 0.0f
        var full_length = data.size -1
        var i = 0
        while (i < full_length+1){
            if (data[i]){
                gammaRate += 2.0f.pow(full_length-i)
            }
            else{
                epsilonRate += 2.0f.pow(full_length-i)
            }
            i += 1
        }
        this.batteryPower = (gammaRate * epsilonRate).toInt()
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
    val sub02 = SubmarineCorrected()
    val input02 = readInput("./data/Day02_02")
    for (command in input02){
        sub02.executeMovement(command)
    }
    val endpoint2 = sub02.depth * sub02.horizontal
    println("Distance depth and forward corrected: $endpoint2")

}

/**
 * Functions implemented for day03
 */
fun day03(){
    val sub = SubmarineCorrected()
    val input = readInput("./data/Day03_01")
    val dataAmount = input.size
    val bitStringLength = input[0].length
    val counts = mutableListOf<Int>()
    for (i in input[0])
        counts.add(0)
    var bitNumber: Int
    var binaryValue: Char
    for (string in input){
        bitNumber = 0
        while (bitNumber < bitStringLength){
            binaryValue = string[bitNumber]
            if (binaryValue == '1'){
                counts[bitNumber] += 1
            }
            bitNumber += 1
        }
    }
    val binCounts = counts.map {(it < dataAmount/2)}
    sub.calculatePower(binCounts)
    println("Calculated submarine power: ${sub.batteryPower}")
}
