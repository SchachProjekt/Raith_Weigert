package pieces;

import chess.Board;
import chess.Square;

public class Pawn extends Piece{

	public Pawn(String colorIn) {
		super(colorIn, "pawn");
		
		if(color == "white"){
			symbol = " B ";
		}
		else{
			symbol = " b ";
		}
	}
	
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		int moveForwardTwo; //variable in der wir später die Richtung ändern | jump mit einrechnen
		int moveForwardOne;
		int startingRow; //reihe an der die Bauern stehn
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		if(!testKing){
			if(toSquare.getType().equals("king")){
				return false;
			}
		}
		
		if(plyColor.equals("white")){ // -für die weißen bauer
			moveForwardTwo = -2;
			moveForwardOne = -1;
			startingRow = 6;
		}
		else{
			moveForwardTwo = 2;
			moveForwardOne = 1;
			startingRow = 1;
		}
			
		if(moveToY == moveFromY + moveForwardOne){
			
			//schmeißen diagonal
			if((moveToX == moveFromX - 1) || (moveToX == moveFromX + 1)){
				if((toSquare.getType() != "blank") && (toSquare.getColor() != plyColor)){
					return true; 
				}
			}	
			//normal move 1 forn
			else if((moveToX == moveFromX) && (toSquare.getType() == "blank")){
				return true;
			}
		}
		//2 foan (jump)
		else if((moveToY == moveFromY + moveForwardTwo) && (moveToX == moveFromX) && (toSquare.getType() == "blank")){
			if(moveFromY == startingRow){ //if pawn moves from the starting row
				return true;
			}
		}
		
		return false;
	}	
}
