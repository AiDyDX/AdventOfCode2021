fun day04() {
    val input: List<String> = readInput("./data/Day04_01")
    val draws: List<Int> = input[0].split(",").map { it.toInt() }
    val boards: MutableList<List<Int>> =
        input.subList(1, input.size).filter { it != "" }
            .map { it1 -> it1.split(" ").filter { it != "" }.map { it.toInt() } } as MutableList<List<Int>>
    val boardSize: Int = boards[0].size
    val horizontalDetected = MutableList(boards.size) { 0 }
    val verticalDetected = MutableList(boards.size) { 0 }
    var finalNumber = 0
    var foundHorizontal = -1
    var foundVertical = -1
    // Runs through each column and row for every draw and adds 1 if the draw is in the row of column. Escapes loop when
    // one of the rows or columns is the same number as the size of the boards
    for (draw in draws) {
        for (rowIndex: Int in boards.indices) {
            val detectedIndex: Int = boards[rowIndex].indexOf(draw)
            if (detectedIndex != -1) {
                horizontalDetected[rowIndex] += 1
                verticalDetected[(rowIndex / boardSize) * boardSize + detectedIndex] += 1
            }
        }
        foundHorizontal = horizontalDetected.indexOf(boardSize)
        foundVertical = verticalDetected.indexOf(boardSize)
        if (foundHorizontal != -1 || foundVertical != -1) {
            finalNumber = draw
            break
        }
    }
    // Gets all the values in the board, removes all values drawn and adds what is left multiplied by final draw
    var winningBoard: MutableList<Int> = ArrayList()
    var score = 0
    if (foundHorizontal != -1) {
        val startRow: Int = (foundHorizontal / boardSize) * boardSize
        for (row in boards.subList(startRow, startRow + boardSize)) {
            for (item in row) {
                winningBoard.add(item)
            }
        }
        winningBoard =
            winningBoard.filterNot { it in draws.subList(0, draws.indexOf(finalNumber) + 1) } as MutableList<Int>
        score = winningBoard.sum() * finalNumber
    }
    // Gets all the values in the board, removes all values drawn and adds what is left multiplied by final draw
    if (foundVertical != -1) {
        val startRow: Int = (foundVertical / boardSize) * boardSize
        for (row in boards.subList(startRow, startRow + boardSize)) {
            for (item in row) {
                winningBoard.add(item)
            }
        }
        winningBoard =
            winningBoard.filterNot { it in draws.subList(0, draws.indexOf(finalNumber) + 1) } as MutableList<Int>
        score = winningBoard.sum() * finalNumber
    }
    println("Winning score of bingo:$score")

    //Task 02
    val draws2: List<Int> = input[0].split(",").map { it.toInt() }
    val boards2: MutableList<List<Int>> =
        input.subList(1, input.size).filter { it != "" }
            .map { it1 -> it1.split(" ").filter { it != "" }.map { it.toInt() } } as MutableList<List<Int>>
    val boardSize2: Int = boards[0].size
    val horizontalDetected2: MutableList<Int> = MutableList(boards2.size) { 0 }
    val verticalDetected2: MutableList<Int> = MutableList(boards2.size) { 0 }
    var finalNumber2 = 0
    var foundHorizontal2: Int
    var foundVertical2: Int
    var finalBoard = false
    for (draw in draws2) {
        for (rowIndex: Int in boards2.indices) {
            val detectedIndex: Int = boards2[rowIndex].indexOf(draw)
            if (detectedIndex != -1) {
                horizontalDetected2[rowIndex] += 1
                verticalDetected2[(rowIndex / boardSize2) * boardSize2 + detectedIndex] += 1
            }
        }

        while (horizontalDetected2.indexOf(boardSize2) != -1) {
            foundHorizontal2 = horizontalDetected2.indexOf(boardSize2)
            if (boards2.size <= boardSize2) {
                finalNumber2 = draw
                finalBoard = true
                break
            }
            val winStartRow: Int = (foundHorizontal2 / boardSize2) * boardSize2
            boards2.subList(winStartRow, winStartRow + boardSize2).clear()
            horizontalDetected2.subList(winStartRow, winStartRow + boardSize2).clear()
            verticalDetected2.subList(winStartRow, winStartRow + boardSize2).clear()
        }
        if (finalBoard) break

        while (verticalDetected2.indexOf(boardSize2) != -1) {
            foundVertical2 = verticalDetected2.indexOf(boardSize2)
            if (boards2.size <= boardSize2) {
                finalNumber2 = draw
                finalBoard = true
                break
            }
            val winStartRow: Int = (foundVertical2 / boardSize2) * boardSize2
            boards2.subList(winStartRow, winStartRow + boardSize2).clear()
            horizontalDetected2.subList(winStartRow, winStartRow + boardSize2).clear()
            verticalDetected2.subList(winStartRow, winStartRow + boardSize2).clear()
        }
        if (finalBoard) break
    }
    var winningBoard2: MutableList<Int> = ArrayList()
    for (row in boards2) {
        for (value in row) {
            winningBoard2.add(value)
        }
    }
    winningBoard2 =
        winningBoard2.filterNot { it in draws2.subList(0, draws2.indexOf(finalNumber2) + 1) } as MutableList<Int>
    val score2: Int = winningBoard2.sum() * finalNumber2
    println("The losing bingo board's score is: $score2")
}