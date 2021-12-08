import kotlin.math.abs
import kotlin.math.pow

fun day07Task01() {
    val input: List<String> = readInput("./data/Day07_01")
    val crabLocations: List<Int> = input.map { it.split(",") }.flatten().map { it.toInt() }
    var leastFuel: Int = crabLocations.maxOrNull()!! * crabLocations.size
    var currentFuel: Int
    val maxLocation: Int? = crabLocations.maxOrNull()
    val minLocation: Int? = crabLocations.minOrNull()

    for (location in minLocation!!..maxLocation!!) {
        currentFuel = 0
        for (crabLocation in crabLocations) {
            currentFuel += abs(crabLocation - location)
            if (currentFuel > leastFuel) break
        }
        leastFuel = if (currentFuel < leastFuel) currentFuel else leastFuel
    }
    println("The least amount of fuel the crabs used is: $leastFuel")
}

fun day07Task02() {
    val input: List<String> = readInput("./data/Day07_01")
    val crabLocations: List<Int> = input.map { it.split(",") }.flatten().map { it.toInt() }
    var leastFuel: Int = (2.0.pow(32.0)).toInt()-1
    var currentFuel: Int
    val maxLocation: Int? = crabLocations.maxOrNull()
    val minLocation: Int? = crabLocations.minOrNull()
    var distance: Int
    for (location in minLocation!!..maxLocation!!) {
        currentFuel = 0
        for (crabLocation in crabLocations) {
            distance = abs(crabLocation - location)
            // Look at nth triangular number. Basically factorial but summation
            currentFuel += (distance * (distance + 1)) / 2
            if (currentFuel > leastFuel) break
        }
        leastFuel = if (currentFuel < leastFuel) currentFuel else leastFuel
    }
    println("The least amount of fuel the crabs used is: $leastFuel")
}