package connectfour

abstract class Player {
	def makeMove(board : Board) : Int
	def getTile : Char
}