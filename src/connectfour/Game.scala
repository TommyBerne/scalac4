package connectfour

object Game extends App {
  var board = new Board(10, 10)
  val players = Array(new Human(board.xTile ), new ComputerPlayer(board.oTile))

  def printBoard = {
    for (y <- board.getHeight to -1 by -1) {
      for (x <- -1 to board.getWidth) {
        print(board.tileAt(x, y))
      }
      print("\n")
    }
  }


  
  while (true) {
    for (i <- 0 to players.length - 1) {
      printBoard
      val player = players(i)
      val chosenColumn = player.makeMove(board)
      val result = board.placeTile(chosenColumn, player.getTile)
      println("result of move=" + result.won)
    }

  }
}