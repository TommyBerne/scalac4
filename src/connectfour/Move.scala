package connectfour

class Move(val board: Board, val col: Int, val row: Int, val won: Boolean, val tile: Char) {
	var score = 0
}