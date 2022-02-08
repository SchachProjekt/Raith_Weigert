package chess;
import pieces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//board is the main class for the game
public class Board {

    static int PORT = 6969;


    static  ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static  Socket clientSocket;

    static {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static  BufferedReader in;

    static {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static  PrintWriter out;

    static {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    final Scanner sc = new Scanner(System.in);


    static final char SIDE_LETTERS[] = {'A','B','C','D','E','F','G','H'};
    static final int SIDE_NUMS[] = {1,2,3,4,5,6,7,8};
    public static Square board[][] = new Square[8][8];

    private static final Scanner scanner = new Scanner(System.in);



    private static void setup(){

        board[0][0] = new Rook("black");
        board[0][1] = new Knight("black");
        board[0][2] = new Bishop("black");
        board[0][3] = new Queen("black");
        board[0][4] = new King("black");
        board[0][5] = new Bishop("black");
        board[0][6] = new Knight("black");
        board[0][7] = new Rook("black");


        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn("black");
        }

        //die leeren
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new BlankSpace();
            }
        }

        for(int i = 0; i < 8; i++){
            board[6][i] = new Pawn("white");
        }

        board[7][0] = new Rook("white");
        board[7][1] = new Knight("white");
        board[7][2] = new Bishop("white");
        board[7][3] = new Queen("white");
        board[7][4] = new King("white");
        board[7][5] = new Bishop("white");
        board[7][6] = new Knight("white");
        board[7][7] = new Rook("white");

    }

    private static String checkForCheckOrMate(String plyColor){ //schach & schachmatt
        String gameover = "";
        int numberOfKings = 0;

        for (int i = 0; i<board.length; i++){
            for (int j = 0; j<board[i].length; j++){
                if(board[i][j].getType().equals("king"))
                {
                    numberOfKings++;
                }
            }
        }
        if(numberOfKings == 1)
        {
            gameover = "true";
        }
        return gameover;
    }

    private static void update(int[] origLoc, int[] newLoc){ //neue Position einnehmen und alte auf blank setzen
        board[newLoc[1]][newLoc[0]] = board[origLoc[1]][origLoc[0]];
        board[origLoc[1]][origLoc[0]] = new BlankSpace();
    }

    private static String draw(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n   ");

        for(char i: SIDE_LETTERS){
            sb.append("  " + i + "  ");
        }
        sb.append("\n   ");

        for(int i = 0; i < 8; i++){
            sb.append(" --- ");
        }

        sb.append("\n");
        for(int i = 0; i < 8; i++){
            sb.append(" " + (8 - i) + " ");

            for(Square j: board[i]){
                sb.append("|" + j.getSymbol() + "|");
            }
            sb.append(" " + (8 - i) + " ");

            sb.append("\n   ");

            for(int j = 0; j < 8; j++){
                sb.append(" --- ");
            }
            sb.append("\n");
        }
        sb.append("   ");
        for(char i: SIDE_LETTERS){
            sb.append("  " + i + "  ");
        }
        sb.append("\n\n");

        return sb.toString();
    }


    private static String getName(int playerNum, String prevName){
        String name;

        while(true){
            System.out.print("Spieler " + playerNum + " bitte name eingeben.\n>> ");
            name = scanner.nextLine().trim();

            //schauen ob namen == sind
            if(!name.isEmpty() && !(name.contains(" ") || name.contains("\t")) && !name.equals(prevName))

                break; //sonst erneut eingeben
            else
                System.out.println("Ungültiger Name. Nochmal eingeben.");
        }
        return name;
    }

    private static String getNameClient(int playerNum, String prevName){ //gleich wie getName nur mit
        String name = "";

        while(true){
            out.print("Spieler 2 bitte name eingeben.\n>> ");
            try {
                name = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //schauen ob namen == sind
            if(!name.isEmpty() && !(name.contains(" ") || name.contains("\t")) && !name.equals(prevName))

                break; //sonst erneut eingeben
            else
                out.println("Ungültiger Name. Nochmal eingeben.");
        }
        return name;
    }

    public int[][] getMoveClient(){ // gleich wie get move nur mit PrintW & IputRead etc.

        int[][] move = new int[2][2];
        for(int runNum = 1; runNum <= 2; runNum++){
            while(true){
                String moveIn = "";
                if(runNum == 1){ //on first run ask for location to move from
                    out.println("Ausgangsposition Eingeben. (zB A7)\n>> "); //prompt

                }

                else{ //on second run ask pos to move to

                    out.println("Zielposition Eingeben. (zb A7)\n>> ");
                }
                try {
                    moveIn = in.readLine();
                } catch (IOException e) {
                    System.out.println("ka wos do passierd is");
                }


                if(!moveIn.isEmpty() && moveIn.length() <= 2 && !(moveIn.contains(" ") || moveIn.contains("\t"))){

                    if(!Character.isDigit(moveIn.charAt(0)) && Character.isDigit(moveIn.charAt(1))){

                        int x, y;
                        Player pp = new Player("sos", "soss");
                        if((x = pp.convertCharToNum(Character.toUpperCase(moveIn.charAt(0)))) != -1){
                            if((y = pp.convertCharNumtoNum(moveIn.charAt(1))) != -1){
                                y = 8 - y; //flipping value so that 0 index is on top
                                int tempArray[] = {x, y};
                                if(runNum == 1){
                                    if(Board.board[y][x].getType() == "blank" || Board.board[y][x].getColor() != "black"){

                                        //returning an array full of -1's if the first location does not point to a piece
                                        //or if the piece is not of the same color as the player
                                        tempArray[0] = -1;
                                        tempArray[1] = -1;
                                        int[][] errorArray = {tempArray, tempArray};
                                        return errorArray;
                                    }
                                }

                                move[runNum - 1] = tempArray;
                                break;
                            }
                        }
                    }
                }
                out.println("Ungültiger Zug. Nochmal versuchen."); //can only get here if the other possibilities fail
            }
        }
        return move;
    }



    public static void main(String[] args)  {

        System.out.println("SCHACH by Raith, Weigert");

        String ply1Name = getName(1, null);
        String ply2Name = getNameClient(2, ply1Name);

        Player whitePly = new Player(ply1Name, "white");
        Player blackPly = new Player(ply2Name, "black");

        setup();


        while(true){

            for(int runNum = 1; runNum <= 2; runNum++){
                System.out.println(draw()); //board hier ausgeben

                out.println(draw()); //board am client ausgeben

                int move[][] = new int[2][2];

                while(true){

                    if(runNum == 1){
                        move = whitePly.getMove();
                    }
                    else{
                        Board bb = new Board();

                        move = bb.getMoveClient();


                    }

                    if(move[0][0] == -1){
                        System.out.println("Ungültiger Zug. Nochmal versuchen.");
                        continue;
                    }

                    int[] moveFrom = move[0];
                    int[] moveTo = move[1];
                    Square fromSquare = board[moveFrom[1]][moveFrom[0]];

                    boolean checkValue;
                    if(runNum == 1){
                        checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false);
                    }
                    else{
                        checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
                    }
                    if(checkValue){
                        update(moveFrom, moveTo);

                        if(runNum == 1){
                            if (checkForCheckOrMate("white") == "true"){
                                System.out.println("Game Over");
                                break;
                            }
                        }
                        else{
                            if (checkForCheckOrMate("black") == "true"){
                                System.out.println("Game Over");
                                break;
                            }
                        }
                        break;
                    }
                    System.out.println("Ungültiger Zug. Nochmal versuchen.");
                }
            }
        }
    }
}

