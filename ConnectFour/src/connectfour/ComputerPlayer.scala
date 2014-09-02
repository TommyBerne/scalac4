package connectfour

class ComputerPlayer(tile: Char) extends Player {
  def makeMove(board: Board): Int = {
    print("\n Please enter a column:")
    Console.readInt
  }

  def getTile: Char = tile
}