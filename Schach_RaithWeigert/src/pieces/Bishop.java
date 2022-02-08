package pieces;

import chess.Board;
import chess.Square;

public class Bishop extends Piece{

    public Bishop(String colorIn) {
        super(colorIn, "bishop");

        if(color.equals("white")){
            symbol = " L ";
        }
        else{
            symbol = " l ";
        }
    }

    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {

        int moveFromX = moveFromReq[0];
        int moveFromY = moveFromReq[1];
        int moveToX = moveToReq[0];
        int moveToY = moveToReq[1];

        Square toSquare = Board.board[moveToY][moveToX];

        //Math . abs gibt den Betrag von etwas zurück

        int moveDistance = Math.abs(moveToX - moveFromX);


        //Überprüft schach
        if(!testKing){
            if(toSquare.getType() == "king"){
                return false;
            }
        }

        String direction; //Bewegungsrichtung

        if(moveToX > moveFromX){
            if(moveToY < moveFromY){
                direction = "topRight";
            }
            else{
                direction = "botRaith";
            }
        }
        else{
            if(moveToY < moveFromY){
                direction = "topLeft";
            }
            else{
                direction = "botLeft";
            }
        }


        Square testSquare; //feld welches überprüft wird


        //for schleife die durch die diagonalen wege des Läufers geht

        for(int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++){

            if(direction == "topRight"){
                testSquare = Board.board[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
            }
            else if(direction == "botRaith"){
                testSquare = Board.board[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
            }
            else if(direction == "topLeft"){
                testSquare = Board.board[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
            }
            else{ //botLeft
                testSquare = Board.board[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
            }

            if((testSquare.getType() != "blank") && (diagMoveAway != moveDistance)){
                return false;
            }
            else if((diagMoveAway == moveDistance) && ((testSquare.getColor() != plyColor) || (testSquare.getType() == "blank"))){
                return true;
            }
        }
        return false;
    }
}
