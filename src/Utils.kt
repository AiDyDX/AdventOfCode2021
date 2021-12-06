import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads lines from the given input txt file and converts them to integer.
 */
fun readInputToInt(name: String) = File("src", "$name.txt").readLines().map{ it.toInt()}

/**
 *
 */
fun countDepthIncreases(depths: List<Int>): Int {
    val depthLength: Int = depths.size
    var depthIncrease: Int = 0
    var i = 1
    while (i < depthLength){
        if (depths[i-1] < depths[i]){
            depthIncrease += 1
        }
        i += 1
    }
    return depthIncrease
}

fun countAddedIncreases(depths: List<Int>): Int{
    val depthLength: Int = depths.size
    var depthIncrease: Int = 0
    var previousAverage: Int = depths[0] + depths[1] + depths[2]
    var currentAverage: Int = 0
    var i = 2
    while (i < depthLength-1){
        currentAverage = depths[i-1] + depths[i] + depths[i+1]
        if (previousAverage < currentAverage){
            depthIncrease += 1
        }
        previousAverage = currentAverage
        i += 1
    }
    return depthIncrease
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
