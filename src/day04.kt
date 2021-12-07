fun day04() {
    val input: List<String> = readInput("./data/Day04_01")
    val draws: List<Int> = input[0].split(",").map { it.toInt() }
    val boards: List<List<Int>> = input.subList(1, input.size).filter { it != "" }.map { it.split(" ").filter { it != "" }.map { it.toInt() } }
    val boardSize: Int = boards[0].size
    var horizontalDetected = MutableList(boards.size){ 0 }
    var verticalDetected = MutableList(boards.size){ 0 }
    var finalNumber: Int = 0
    var foundHorizontal: Int = -1
    var foundVertical: Int = -1
    // Runs through each column and row for every draw and adds 1 if the draw is in the row of column. Escapes loop when
    // one of the rows or columns is the same number as the size of the boards
    for (draw in draws){
        for (rowIndex: Int in boards.indices){
            var detectedIndex: Int = boards[rowIndex].indexOf(draw)
            if (detectedIndex != -1){
                horizontalDetected[rowIndex] += 1
                verticalDetected[(rowIndex/boardSize)*boardSize + detectedIndex] += 1
            }
        }
        foundHorizontal = horizontalDetected.indexOf(boardSize)
        foundVertical = verticalDetected.indexOf(boardSize)
        if (foundHorizontal != -1 || foundVertical != -1){
            finalNumber = draw
            break
        }
    }
    // Gets all the values in the board, removes all values drawn and adds what is left multiplied by final draw
    var winningBoard: MutableList<Int> = ArrayList()
    var score: Int = 0
    if (foundHorizontal != -1){
        var startRow: Int = (foundHorizontal/boardSize)*boardSize
        for (row in boards.subList(startRow, startRow+boardSize)){
            for (item in row){
                winningBoard.add(item)
            }
        }
        winningBoard = winningBoard.filterNot { it in draws.subList(0, draws.indexOf(finalNumber)+1) } as MutableList<Int>
        score = winningBoard.sum()*finalNumber
    }
    // Gets all the values in the board, removes all values drawn and adds what is left multiplied by final draw
    if (foundVertical != -1){
        val startRow: Int = (foundVertical/boardSize)*boardSize
        for (row in boards.subList(startRow, startRow+boardSize)){
            for (item in row){
                winningBoard.add(item)
            }
        }
        winningBoard = winningBoard.filterNot { it in draws.subList(0, draws.indexOf(finalNumber)+1) } as MutableList<Int>
        score = winningBoard.sum()*finalNumber
    }
    println("Winning score of bingo:$score")

    //Task 02
}