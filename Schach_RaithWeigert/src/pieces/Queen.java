package pieces;

import chess.Board;
import chess.Square;

public class Queen extends Piece{

	public Queen(String colorIn) {
		super(colorIn, "queen");
		
		if(color == "white"){
			symbol = " D ";
		}
		else{
			symbol = " d ";
		}
	}

	@Override
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		String direction;
		String type; //diagonal od grade


		if(!testKing){
			if(toSquare.getType().equals("king")){
				return false;
			}
		}
		
		if(moveToY == moveFromY){ //alle möglichen bewegungs richtungen
			if(moveToX > moveFromX){
				direction = "rite";
				type = "straight";
			}
			else{
				direction = "left";
				type = "straight";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
				type = "straight";
			}
			else{
				direction = "top";
				type = "straight";
			}
		}
		else if(moveToX > moveFromX){
			if(moveToY < moveFromY){
				direction = "topRite";
				type = "diagonal";
			}
			else{
				direction = "botRite";
				type = "diagonal";
			}
		}
		else if(moveToX < moveFromX){
			if(moveToY < moveFromY){
				direction = "topLeft";
				type = "diagonal";
			}
			else{
				direction = "botLeft";
				type = "diagonal";
			}
		}
		else{
			return false;
		}
		
		Square testSquare;
		
		if(type.equals("diagonal")){
			int moveDistance = Math.abs(moveToX - moveFromX);
		
			for(int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++){
			
				if(direction.equals("topRite")){
					testSquare = Board.board[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
				}
				else if(direction.equals("botRite")){
					testSquare = Board.board[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
				}
				else if(direction.equals("topLeft")){
					testSquare = Board.board[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
				}
				else{ //botLeft
					testSquare = Board.board[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
				}
			
				if((testSquare.getType() != "blank") && (diagMoveAway != moveDistance)){
					return false;
				}
				else if((diagMoveAway == moveDistance) && ((testSquare.getColor() != plyColor) || (testSquare.getType().equals("blank")))){
					return true;
				}
			}
		}
		else{
			if((direction.equals("rite")) || (direction.equals("left"))){
				int displaceMax = Math.abs(moveToX - moveFromX);
		
				for(int displace = 1; displace <= displaceMax; displace++){
					if(direction.equals("rite")){
						testSquare = Board.board[moveFromY][moveFromX + displace];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType().equals("blank")) || (testSquare.getColor() != plyColor))){
							return true;
						}
					}
					else{
						testSquare = Board.board[moveFromY][moveFromX - displace];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType().equals("blank")) || (testSquare.getColor() != plyColor))){
							return true;
						}
					}
				}
			}
			else{
				int displaceMax = Math.abs(moveToY - moveFromY);
				
				for(int displace = 1; displace <= displaceMax; displace++){
				
					if(direction == "top"){
						testSquare = Board.board[moveFromY - displace][moveFromX];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType().equals("blank")) || (testSquare.getColor() != plyColor))){
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
		}
		return false;
	}

}
