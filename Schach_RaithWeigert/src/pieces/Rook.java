package pieces;

import chess.Board;
import chess.Square;

public class Rook extends Piece{

	public Rook(String colorIn) {
		super(colorIn, "rook");
		
		if(color == "white"){
			symbol = " T ";
		}
		else{
			symbol = " t ";
		}
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		String direction;
		
		if(!testKing){
			if(toSquare.getType().equals("king")){
				return false; //can't move to take a king
			}
		}
		
		if(moveToY == moveFromY){
			if(moveToX > moveFromX){
				direction = "raith";
			}
			else{
				direction = "left";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
			}
			else{
				direction = "top";
			}
		}
		else{
			return false;
		}
		
		Square testSquare;
		
		if((direction == "raith") || (direction == "left")){
			int displaceMax = Math.abs(moveToX - moveFromX); //max offset
		
			for(int displace = 1; displace <= displaceMax; displace++){ // ganzen weg durchgehen
				if(direction == "raith"){
					testSquare = Board.board[moveFromY][moveFromX + displace];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[moveFromY][moveFromX - displace];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
			}
		}
		else{ //oben od unten
			int displaceMax = Math.abs(moveToY - moveFromY); //maxales offset
				
			for(int displace = 1; displace <= displaceMax; displace++){ //alle felder des weges durchgehen
				
				if(direction == "top"){
					testSquare = Board.board[moveFromY - displace][moveFromX];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[moveFromY + displace][moveFromX];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType().equals("blank")) || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
			}
		}
		return false;
	}
}
