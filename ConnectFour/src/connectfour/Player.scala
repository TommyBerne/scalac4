package connectfour

import connectfour.Board

abstract class Player {
	def makeMove(board : Board) : Int
	def getTile : Char
}