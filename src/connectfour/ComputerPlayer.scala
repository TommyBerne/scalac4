package connectfour

class ComputerPlayer(tile: Char) extends Player {
  val SEARCH_DEPTH = 5

  def makeMove(board: Board): Int = {
    findBestMove(board, tile, SEARCH_DEPTH).col
  }

  def scoreWithHeuristic(move: Move): Int = 0

  def findBestMove(board: Board, tile: Char, depthToGo: Int): Move = {
    val SCORE_INVALID = Int.MinValue
    val SCORE_WIN = Int.MaxValue - 1 - SEARCH_DEPTH + depthToGo
    /* EXPLANATION OF SCORE_WIN...
    * Int.MaxValue  : cause winning is good
    * -1            : so that losing the next turn will be better than invalid
    * + depthToGo   : so that soon wins are better than distant ones
    * -SEARCH_DEPTH : so that +depthToGo doesn't cause overflow
    */
    
    val nextPlayerTile = { if (tile == board.xTile) board.oTile else board.xTile }

    var bestMove = new Move(null, -1, -1, false, board.emptyTile)
    bestMove.score = SCORE_INVALID
    for (col <- 0 to board.width - 1) {
      val nextBoard = board.copyBoard
      val move = nextBoard.placeTile(col, tile)
      if (move != null) {
        if (move.won) {
          move.score = SCORE_WIN
          return move
        } else if (depthToGo == 0) {
          move.score = scoreWithHeuristic(move)
        } else {
          val nextPlayerMove = findBestMove(nextBoard, nextPlayerTile, depthToGo - 1)
          move.score = -nextPlayerMove.score
        }

        if (move.score > bestMove.score) bestMove = move
      }
    }
    return bestMove
  }

  def getTile: Char = tile
}