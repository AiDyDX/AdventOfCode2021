fun day06Task01(days: Int = 80) {
    val input: List<String> = readInput("./data/Day06_01")
    val lanternFishes: MutableList<Int> = input.map { it.split(",") }.flatten().map { it.toInt() } as MutableList<Int>
    val newFishes: MutableList<Int> = mutableListOf()
    for (day in 1..days) {
        for (fishIndex in lanternFishes.indices) {
            lanternFishes[fishIndex] -= 1
            if (lanternFishes[fishIndex] < 0) {
                lanternFishes[fishIndex] = 6
                newFishes.add(8)
            }
        }
        lanternFishes.addAll(newFishes)
        newFishes.clear()

    }
    val finalFishes: Int = lanternFishes.count()
    println("Total fishes on day $days: $finalFishes")
}


fun day06Task02(days: Int = 80) {
    val input: List<String> = readInput("./data/Day06_01")
    val lanternFishes: MutableList<Int> = input.map { it.split(",") }.flatten().map { it.toInt() } as MutableList<Int>
    val fertilityDays1: MutableList<ULong> = mutableListOf()
    val fertilityDays2: MutableList<ULong> = mutableListOf()
    for (i in 0..8) {
        fertilityDays1.add(lanternFishes.count { it == i }.toULong())
        fertilityDays2.add(0u)
    }
    for (day in 1..days) {
        if (day % 2 != 0) {
            for (fertilityAmount in 1..8) {
                fertilityDays2[fertilityAmount - 1] = fertilityDays1[fertilityAmount]
            }
            fertilityDays2[6] += fertilityDays1[0]
            fertilityDays2[8] = fertilityDays1[0]

        } else {
            for (fertilityAmount in 1..8) {
                fertilityDays1[fertilityAmount - 1] = fertilityDays2[fertilityAmount]
            }
            fertilityDays1[6] += fertilityDays2[0]
            fertilityDays1[8] = fertilityDays2[0]

        }

    }
    val finalFishes = if (fertilityDays1.sum() > fertilityDays2.sum()) fertilityDays1.sum() else fertilityDays2.sum()
    println("Total fishes on day $days: $finalFishes")
}