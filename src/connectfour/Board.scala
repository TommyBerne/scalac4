package connectfour

class Board(width: Int, height: Int) {
  val xTile = 'X'
  val oTile = 'O'
  val emptyTile = ' '
  val borderTile = '#'
  val winningLineSize = 4
  var tiles = Array.fill[Char](width, height)(emptyTile) //Array.ofDim[Char](width, height)

  def getWidth: Int = width
  def getHeight: Int = height

  def tileAt(column: Int, row: Int): Char = {
    if (column < 0 || row < 0 || column >= width || row >= height) borderTile
    else tiles(column)(row)
  }

  def checkForWinAt(column: Int, row: Int): Boolean = {
    val playerTile = tileAt(column, row)
    def checkByIncrement(inc: (Int, Int)): Boolean = {
      println("checking by in:" + inc)
      var line = 0
      for (i <- -winningLineSize to winningLineSize) {
        val x = column + i * inc._1
        val y = row + i * inc._2
        println("checking [" + x + "," + y + "] in case " + tileAt(x, y) + "==" + playerTile + " line is" + line)
        if (tileAt(x, y) == playerTile) {
          line += 1
          if (line == winningLineSize)
            return true
        } else {
          line = 0
        }
      }
      println("returning false")
      return false
    }
    val vertical = (0, 1)
    val horizontal = (1, 0)
    val diagDown = (1, 1)
    val diagUp = (1, -1)

    return (checkByIncrement(vertical) || checkByIncrement(horizontal) || checkByIncrement(diagDown) || checkByIncrement(diagUp))
  }

  def findTopEmptyRow(column: Int): Int = {
    for (y <- 0 to height) {
      if (tileAt(column, y) == emptyTile) return y;
    }
    -1
  }

  def isColumnFree(column: Int): Boolean = {
    tileAt(column, height - 1) == emptyTile
  }

  def copyBoard: Board = { this }

  def placeTile(column: Int, tile: Char): Move = {
    val row = findTopEmptyRow(column)
    if (row > -1) {
      tiles(column)(row) = tile
      val won = checkForWinAt(column, row)
      val move = new Move(this.copyBoard, column, row, won, tile)
      return move
    } else {
      return null
    }
  }

}