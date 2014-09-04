package connectfour;

public class ComputerJavaPlayer extends Player {
	private char tile;
	public ComputerJavaPlayer(char tile){
		this.tile = tile;
	}
	
	public char getTile(){
		return tile;
	}
	
	public int makeMove(Board board) {
		return 0;
	}
}
