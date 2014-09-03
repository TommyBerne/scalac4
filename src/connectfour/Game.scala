package connectfour

object Game extends App {
  var board = new Board(10, 10)
  var won = false;
  val players = Array(new Human(board.xTile), new ComputerPlayer(board.oTile))

  def printBoard = {

    print("|#|")
    for (x <- 0 to board.getWidth - 1) {
      print(x)
      print("|")
    }
    print("#|\n|")
    for (y <- board.getHeight to -1 by -1) {
      for (x <- -1 to board.getWidth) {
        print(board.tileAt(x, y))
        print("|")
      }
      print("\n|")
    }
    print("#|")
    for (x <- 0 to board.getWidth - 1) {
      print(x)
      print("|")
    }
    print("#|\n")
  }

  while (!won) {
    for (i <- 0 to players.length - 1) {
      printBoard
      val player = players(i)
      val chosenColumn = player.makeMove(board)
      val lastMove = board.placeTile(chosenColumn, player.getTile)
      if (lastMove.won == true) {
        for (i <- 0 to 3) println("Player " + lastMove.tile + " has won!!!")
        won = lastMove.won
      }
    }

  }
}