package pieces;

import chess.Board;
import chess.Square;

public class Knight extends Piece{

	public Knight(String colorIn) {
		super(colorIn, "knight");
		
		if(color.equals("white")){
			symbol = " S ";
		}
		else{
			symbol = " s ";
		}
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];

		//Auf schach überprüfen
		if(!testKing){
			if(toSquare.getType().equals("king")){
				return false;
			}
		}
		
		boolean isLegalForKnight = false;
		
		for(int displaceX = -2; displaceX <= 2; displaceX++){

			if(displaceX != 0){
				if(moveToX == moveFromX + displaceX){
					
					if(Math.abs(displaceX) == 1){
						//wenn x 1 war muss y 2 sein

						for(int displaceY = -2; displaceY <= 2; displaceY += 4){
							if(moveToY == moveFromY + displaceY){
								isLegalForKnight = true;
							}
						}
					}
					else{
						//wenn x 2 war muss y 1 sein
						for(int displaceY = -1; displaceY <= 1; displaceY += 2){
							if(moveToY == moveFromY + displaceY){
								isLegalForKnight = true;
							}
						}
					}
				}
			}
		}
		if(isLegalForKnight){ //wenn true und ein valides feld return true

			if((toSquare.getType().equals("blank")) || (toSquare.getColor() != plyColor)){
				return true;
			}
		}
		
		return false;
	}
}
