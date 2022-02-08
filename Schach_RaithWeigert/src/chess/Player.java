package chess;

import java.util.Scanner;

class Player {
    private String name;
    private String color;
    private final Scanner scanner = new Scanner(System.in);

    Player(String nameIn, String colorIn){
        name = nameIn;
        color = colorIn;
    }

    public int convertCharToNum(char charIn){

        int numOut = -1;

        for(int i = 0; i < Board.SIDE_LETTERS.length; i++){

            if(Board.SIDE_LETTERS[i] == charIn){
                numOut = i;
            }
        }
        return numOut;
    }

    public int convertCharNumtoNum(char charIn){
        int numOut = -1;
        int convertedNum = Character.getNumericValue(charIn);

        for(int i: Board.SIDE_NUMS){
            if(i == convertedNum){

                numOut = convertedNum;
            }
        }
        return numOut;
    }

    public int[][] getMove(){

        int[][] move = new int[2][2];
        for(int runNum = 1; runNum <= 2; runNum++){
            while(true){
                String moveIn = "";
                if(runNum == 1){
                    System.out.print(name + ", Ausgangsposition eingeben. (zb A2)\n>> "); //prompt

                }

                else{ //on second run ask pos to move to

                    System.out.print(name + ", Zieposition eingeben. (zb A3)\n>> ");
                }
                moveIn = scanner.nextLine().trim();


                if(!moveIn.isEmpty() && moveIn.length() <= 2 && !(moveIn.contains(" ") || moveIn.contains("\t"))){

                    if(!Character.isDigit(moveIn.charAt(0)) && Character.isDigit(moveIn.charAt(1))){

                        int x, y;

                        if((x = convertCharToNum(Character.toUpperCase(moveIn.charAt(0)))) != -1){
                            if((y = convertCharNumtoNum(moveIn.charAt(1))) != -1){
                                y = 8 - y;
                                int tempArray[] = {x, y};
                                if(runNum == 1){
                                    if(Board.board[y][x].getType() == "blank" || Board.board[y][x].getColor() != color){


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
                System.out.println("Ungültiger Zug. Nochmal versuchen.");
            }
        }
        return move;
    }
}