/**
 * Functions implemented for day03
 */
fun day03() {
    //Task01
    val input: List<List<Int>> =
        readInput("./data/Day03_01").map { row -> row.split("").filter { it.isNotBlank() }.map { it.toInt() } }
    val mostCommonBits: MutableList<Int> = mutableListOf()
    for (column: Int in input.first().indices) {
        val mostCommonBit: Int = if ((input.sumOf { it[column] }.toFloat() / input.count().toFloat()) > 0.5) 1 else 0
        mostCommonBits.add(mostCommonBit)
    }
    val leastCommonBits: List<Int> = mostCommonBits.map { it.xor(1) }
    val gamma: Int = Integer.parseInt(mostCommonBits.joinToString(""), 2)
    val epsilon: Int = Integer.parseInt(leastCommonBits.joinToString(""), 2)
    println("Power left:${gamma * epsilon}")


    //task 2
    val input1: List<List<Int>> =
        readInput("./data/Day03_01").map { row -> row.split("").filter { it.isNotBlank() }.map { it.toInt() } }
    var oxygenInput: List<List<Int>> = input1.toMutableList()
    var carbonScrubbingInput: List<List<Int>> = input1.toMutableList()
    var oxygenValue: Int = 0
    var carbonValue: Int = 0
    for (column: Int in input1.first().indices) {
        val mostCommonBit: Int =
            if ((oxygenInput.sumOf { it[column] }.toFloat() / oxygenInput.count().toFloat()) >= 0.5) 1 else 0
        oxygenInput = oxygenInput.filter { it[column] == mostCommonBit }
        if (oxygenInput.size == 1) {
            oxygenValue = Integer.parseInt(oxygenInput.first().joinToString(""), 2)
            break
        }
    }
    for (column: Int in input1.first().indices) {
        val leastCommonBit: Int =
            if ((carbonScrubbingInput.sumOf { it[column] }.toFloat() / carbonScrubbingInput.count()
                    .toFloat()) >= 0.5
            ) 0 else 1
        carbonScrubbingInput = carbonScrubbingInput.filter { it[column] == leastCommonBit }
        if (carbonScrubbingInput.size == 1) {
            carbonValue = Integer.parseInt(carbonScrubbingInput.first().joinToString(""), 2)
            break
        }
    }
    println("Diagnostic oxygen within the submarine: ${oxygenValue * carbonValue}")
}