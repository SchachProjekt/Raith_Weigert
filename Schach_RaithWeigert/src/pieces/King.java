package pieces;

import chess.Board;
import chess.Square;

public class King extends Piece{
	public King(String colorIn) {
		super(colorIn, "king");
		
		if(this.color == "white"){
			symbol = " K ";
		}
		else{
			symbol = " k ";
		}
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];

		//alle Richtungen ein Feld durchgehen
		for (int moveAwayX = -1; moveAwayX <= 1; moveAwayX++){
			for (int moveAwayY = -1; moveAwayY <= 1; moveAwayY++){
				if(moveToX == moveFromX + moveAwayX && moveToY == moveFromY + moveAwayY){
					if((toSquare.getType() != "blank") && (toSquare.getColor() != plyColor)){
						return true;
					}
					else if(toSquare.getType() == "blank"){
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
