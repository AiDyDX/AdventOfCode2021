fun day05Task01() {
    val input: List<String> = readInput("./data/Day05_01")
    val ventRanges = input.map { it1 -> it1.split(" -> ").map { it2 -> it2.split(",").map { it.toInt() } } }
    val maxX: Int? = ventRanges.flatten().flatten().filterIndexed { index, _ -> index % 2 == 0 }.maxOrNull()
    val maxY: Int? = ventRanges.flatten().flatten().filterIndexed { index, _ -> index % 2 != 0 }.maxOrNull()
    val locations: MutableList<MutableList<Int>> = MutableList(maxX!! + 1) { MutableList(maxY!! + 1) { 0 } }
    var x1: Int
    var x2: Int
    var y1: Int
    var y2: Int
    for (set in ventRanges) {
        // If X values are the same (Vertical items differ)
        if (set[0][0] == set[1][0]) {
            if (set[0][1] > set[1][1]) {
                y1 = set[1][1]
                y2 = set[0][1]
            } else {
                y1 = set[0][1]
                y2 = set[1][1]
            }
            for (vents in y1..y2) {
                locations[set[0][0]][vents] += 1
            }
        }
        // If Y values are the same (Horizontal items differ)
        else if (set[0][1] == set[1][1]) {
            if (set[0][0] > set[1][0]) {
                x1 = set[1][0]
                x2 = set[0][0]
            } else {
                x1 = set[0][0]
                x2 = set[1][0]
            }
            for (vents in x1..x2) {
                locations[vents][set[0][1]] += 1
            }
        }
    }
    val dangers: Int = locations.flatten().count { it > 1 }
    println("Total dangerous hydrothermal vents in area: $dangers")
}

fun day05Task02() {
    val input: List<String> = readInput("./data/Day05_01")
    val ventRanges = input.map { it1 -> it1.split(" -> ").map { it2 -> it2.split(",").map { it.toInt() } } }
    val maxX: Int? = ventRanges.flatten().flatten().filterIndexed { index, _ -> index % 2 == 0 }.maxOrNull()
    val maxY: Int? = ventRanges.flatten().flatten().filterIndexed { index, _ -> index % 2 != 0 }.maxOrNull()
    val locations: MutableList<MutableList<Int>> = MutableList(maxX!! + 1) { MutableList(maxY!! + 1) { 0 } }
    var x2 = 0
    var x1 = 0
    var y1 = 0
    var y2 = 0
    for (set in ventRanges) {

        // Diagonal vents
        if (set[0][0] != set[1][0] && set[0][1] != set[1][1]) {
            x1 = set[0][0]
            x2 = set[1][0]
            y1 = set[0][1]
            y2 = set[1][1]
            if (x2 > x1 && y2 > y1) {
                while (x1 <= x2) {
                    locations[x1][y1] += 1
                    x1 += 1
                    y1 += 1
                }
                continue
            }
            if (x2 < x1 && y2 < y1) {
                while (x1 >= x2) {
                    locations[x1][y1] += 1
                    x1 -= 1
                    y1 -= 1
                }
                continue
            }
            if (x2 > x1 && y2 < y1) {
                while (x1 <= x2) {
                    locations[x1][y1] += 1
                    x1 += 1
                    y1 -= 1
                }
                continue
            }
            if (x2 < x1 && y2 > y1) {
                while (x1 >= x2) {
                    locations[x1][y1] += 1
                    x1 -= 1
                    y1 += 1
                }
                continue
            }
        } else {
            if (set[0][1] > set[1][1]) {
                y1 = set[1][1]
                y2 = set[0][1]
            } else if (set[0][1] < set[1][1]) {
                y1 = set[0][1]
                y2 = set[1][1]
            }
            if (set[0][0] > set[1][0]) {
                x1 = set[1][0]
                x2 = set[0][0]
            } else if (set[0][0] < set[1][0]) {
                x1 = set[0][0]
                x2 = set[1][0]
            }
            // If X values are the same (Vertical items differ)
            if (set[0][0] == set[1][0]) {
                for (vents in y1..y2) {
                    locations[set[0][0]][vents] += 1
                }
                continue
            }
            // If Y values are the same (Horizontal items differ)
            else if (set[0][1] == set[1][1]) {
                for (vents in x1..x2) {
                    locations[vents][set[0][1]] += 1
                }
                continue
            }
        }
    }
    val dangers: Int = locations.flatten().count { it > 1 }
    println("Total dangerous hydrothermal vents in area(with diagonals): $dangers")
}