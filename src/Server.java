import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class Server {
    public static Figure[][] board = new Figure[8][8];
    static boolean checkMate;
    public static int player = 1;

    public static void main(String[] args) {
        play();
    }


    public static void play() {
        fillBoard();
        printBoard();
        chooseName();

        int yStart = -1;
        int yEnd = -1;

        while (checkMate == false) {


                char[] movesAsCharArray = getMove().toUpperCase(Locale.ROOT).toCharArray();
                switch (movesAsCharArray[0]) {
                    case 'A':
                        yStart = 0;
                        break;
                    case 'B':
                        yStart = 1;
                        break;
                    case 'C':
                        yStart = 2;
                        break;
                    case 'D':
                        yStart = 3;
                        break;
                    case 'E':
                        yStart = 4;
                        break;
                    case 'F':
                        yStart = 5;
                        break;
                    case 'G':
                        yStart = 6;
                        break;
                    case 'H':
                        yStart = 7;
                        break;
                }

                switch (movesAsCharArray[3]) {
                    case 'A':
                        yStart = 0;
                        break;
                    case 'B':
                        yStart = 1;
                        break;
                    case 'C':
                        yStart = 2;
                        break;
                    case 'D':
                        yStart = 3;
                        break;
                    case 'E':
                        yStart = 4;
                        break;
                    case 'F':
                        yStart = 5;
                        break;
                    case 'G':
                        yStart = 6;
                        break;
                    case 'H':
                        yStart = 7;
                        break;
                }
                    move(movesAsCharArray[1], yStart + 1, movesAsCharArray[4], yEnd + 1);
                    if (player == 1) {
                        player = 2;
                    } else if (player == 2) {
                        player = 1;
                    }
                }


        }


    private static boolean isValidMove(char figureType, Figure figureToMove, String move) {
        ArrayList<String> valideMoves = new ArrayList<>();
        switch (figureType) {
            case 'B':

                if (player == 2) {
                    valideMoves = howToMoveBlackPawn(figureToMove);
                } else {
                    valideMoves = howToMovePawn(figureToMove);
                }
                break;

            case 'T':
                valideMoves = howToMoveRook(figureToMove);
                break;

            case 'L':
                valideMoves = howToMoveBishop(figureToMove);
                break;

            case 'S':
                valideMoves = howToMoveKnight(figureToMove);
                break;

            case 'D':
                valideMoves = howToMoveQueen(figureToMove);
                break;

            case 'K':
                valideMoves = howToMoveKing(figureToMove);
                break;


        }
        return valideMoves.contains(move);

    }

    private static ArrayList<String> howToMoveRook(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        boolean moveValide = false;
        int xCord = figureToMove.x;
        int yCord = figureToMove.y;

        do {
            for (int i = xCord; i < 7; i++) {
                if ((board[i][yCord].type == ' ')) {
                    moveValide = true;
                    valideMoves.add(i + "," + yCord);

                } else if (figureToMove.team != board[i][yCord].team) {
                    moveValide = true;
                    valideMoves.add(i + "," + yCord);
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        }
        while (moveValide == true);

        do {
            for (int i = yCord; i < 7; i++) {
                if ((board[xCord][i].type == ' ')) {
                    moveValide = true;
                    valideMoves.add(i + "," + xCord);

                } else if (figureToMove.team != board[i][xCord].team) {
                    moveValide = true;
                    valideMoves.add(i + "," + xCord);
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        }
        while (moveValide == true);


        do {
            for (int i = xCord; i > 0; i--) {
                if ((board[i][yCord].type == ' ')) {
                    moveValide = true;
                    valideMoves.add(i + "," + yCord);

                } else if (figureToMove.team != board[i][yCord].team) {
                    moveValide = true;
                    valideMoves.add(i + "," + yCord);
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        }
        while (moveValide == true);

        do {
            for (int i = yCord; i > 0; i--) {
                if ((board[xCord][i].type == ' ')) {
                    moveValide = true;
                    valideMoves.add(i + "," + xCord);

                } else if (figureToMove.team != board[i][xCord].team) {
                    moveValide = true;
                    valideMoves.add(i + "," + xCord);
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        }
        while (moveValide == true);


        return valideMoves;
    }


    private static ArrayList<String> howToMoveBishop(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        boolean moveValide = false;
        int xCord = figureToMove.x;
        int yCord = figureToMove.y;

        do {
            for (int i = 0; (i < 7) || (i > 0); i++) {
                if ((board[xCord + 1][yCord + 1].type == ' ')) {
                    moveValide = true;
                    valideMoves.add((xCord + 1) + "," + (yCord + i));

                } else if (figureToMove.team != board[xCord + i][yCord + i].team) {
                    moveValide = true;
                    valideMoves.add((xCord + i) + "," + (yCord + i));
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        } while (moveValide == true);

        do {
            for (int i = 0; (i < 7) || (i > 0); i++) {
                if ((board[xCord - 1][yCord + 1].type == ' ')) {
                    moveValide = true;
                    valideMoves.add((xCord - 1) + "," + (yCord + i));

                } else if (figureToMove.team != board[xCord + i][yCord + i].team) {
                    moveValide = true;
                    valideMoves.add((xCord - i) + "," + (yCord + i));
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        } while (moveValide == true);

        do {
            for (int i = 0; (i < 7) || (i > 0); i++) {
                if ((board[xCord + 1][yCord - 1].type == ' ')) {
                    moveValide = true;
                    valideMoves.add((xCord + 1) + "," + (yCord - i));

                } else if (figureToMove.team != board[xCord + i][yCord - i].team) {
                    moveValide = true;
                    valideMoves.add((xCord + i) + "," + (yCord - i));
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        } while (moveValide == true);

        do {
            for (int i = 0; (i < 7) || (i > 0); i++) {
                if ((board[xCord - 1][yCord - 1].type == ' ')) {
                    moveValide = true;
                    valideMoves.add((xCord - 1) + "," + (yCord - i));

                } else if (figureToMove.team != board[xCord - i][yCord - i].team) {
                    moveValide = true;
                    valideMoves.add((xCord - i) + "," + (yCord - i));
                    i = 10;

                } else {
                    moveValide = false;
                }
            }
        } while (moveValide == true);

        return valideMoves;
    }


    private static ArrayList<String> howToMoveKnight(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        int xCord = figureToMove.x;
        int yCord = figureToMove.y;

        try {
            if ((board[xCord + 1][yCord + 2].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord + 1) + "," + (yCord + 2));
            }
        } catch (Exception e) {

        }

        try {
            if ((board[xCord + 2][yCord + 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord + 2) + "," + (yCord + 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord + 2][yCord - 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord + 2) + "," + (yCord - 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord + 1][yCord - 2].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord + 1) + "," + (yCord - 2));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 2][yCord + 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord - 2) + "," + (yCord + 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 1][yCord + 2].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord - 1) + "," + (yCord + 2));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 2][yCord - 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord - 2) + "," + (yCord - 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 1][yCord - 2].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord - 1) + "," + (yCord - 2));
            }
        } catch (Exception e) {

        }
        return valideMoves;
    }

    private static ArrayList<String> howToMoveQueen(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        valideMoves.addAll(howToMoveBishop(figureToMove));
        valideMoves.addAll(howToMoveRook(figureToMove));
        return valideMoves;
    }

    private static ArrayList<String> howToMoveKing(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        int xCord = figureToMove.x;
        int yCord = figureToMove.y;

        try {
            if ((board[xCord + 1][yCord].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord + 1) + "," + (yCord));
            }
        } catch (Exception e) {

        }

        try {
            if ((board[xCord][yCord + 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord) + "," + (yCord + 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord][yCord - 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord) + "," + (yCord - 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord + 1][yCord + 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord + 1) + "," + (yCord));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 1][yCord - 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord) + "," + (yCord + 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 1][yCord].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord - 1) + "," + (yCord));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord + 1][yCord - 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord) + "," + (yCord - 1));
            }
        } catch (Exception e) {

        }
        try {
            if ((board[xCord - 1][yCord + 1].type == ' ') || (board[xCord][yCord].team != figureToMove.team)) {
                valideMoves.add((xCord - 1) + "," + (yCord));
            }
        } catch (Exception e) {

        }
        return valideMoves;

    }

    private static ArrayList<String> howToMoveBlackPawn(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        int xCord = figureToMove.x;
        int yCord = figureToMove.y;
        if ((figureToMove.hasMoved == false) && (board[xCord][yCord - 1].type == ' ')) {
            valideMoves.add(xCord+","+(yCord - 2));
        }
        if (board[xCord][yCord - 1].type == ' ') {
            valideMoves.add(xCord+","+(yCord - 1));
        }
        if (board[xCord+1][yCord - 1].type == ' ') {
            valideMoves.add((xCord+1)+","+(yCord - 1));
        }
        if (board[xCord-1][yCord - 1].type == ' ') {
            valideMoves.add((xCord-1)+","+(yCord - 1));
        }
        return valideMoves;
    }

    private static ArrayList<String> howToMovePawn(Figure figureToMove) {
        ArrayList<String> valideMoves = new ArrayList<>();
        int xCord = figureToMove.x;
        int yCord = figureToMove.y;
        if ((figureToMove.hasMoved == false) && (board[xCord][yCord + 1].type == ' ')) {
            valideMoves.add(xCord+","+(yCord + 2));
        }
        if (board[xCord][yCord - 1].type == ' ') {
            valideMoves.add(xCord+","+(yCord + 1));
        }
        if (board[xCord+1][yCord - 1].type == ' ') {
            valideMoves.add((xCord+1)+","+(yCord + 1));
        }
        if (board[xCord-1][yCord - 1].type == ' ') {
            valideMoves.add((xCord-1)+","+(yCord + 1));
        }
        return valideMoves;
    }


    private static void chooseName() {
        System.out.println("Bitte geben Sie einen Benutzernamen ein");
    }

    private static void fillBoard() {

        board[0][0] = new Rook(0, 0,1);
        board[0][1] = new Knight(0, 1,1);
        board[0][2] = new Bishop(0, 2,1);
        board[0][3] = new Queen(0, 3,1);
        board[0][4] = new King(0, 4,1);
        board[0][5] = new Bishop(0, 5,1);
        board[0][6] = new Knight(0, 6,1);
        board[0][7] = new Rook(0, 7,1);

        board[1][0] = new Pawn(1, 0);
        board[1][1] = new Pawn(1, 1);
        board[1][2] = new Pawn(1, 2);
        board[1][3] = new Pawn(1, 3);
        board[1][4] = new Pawn(1, 4);
        board[1][5] = new Pawn(1, 5);
        board[1][6] = new Pawn(1, 6);
        board[1][7] = new Pawn(1, 7);

        board[7][0] = new Rook(7, 0,2);
        board[7][1] = new Knight(7, 1,2);
        board[7][2] = new Bishop(7, 2,2);
        board[7][3] = new Queen(7, 3,2);
        board[7][4] = new King(7, 4,2);
        board[7][5] = new Bishop(7, 5,2);
        board[7][6] = new Knight(7, 6,2);
        board[7][7] = new Rook(7, 7,2);

        board[6][0] = new BlackPawn(6, 0);
        board[6][1] = new BlackPawn(6, 1);
        board[6][2] = new BlackPawn(6, 2);
        board[6][3] = new BlackPawn(6, 3);
        board[6][4] = new BlackPawn(6, 4);
        board[6][5] = new BlackPawn(6, 5);
        board[6][6] = new BlackPawn(6, 6);
        board[6][7] = new BlackPawn(6, 7);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null)
                    board[i][j] = new Empty(i, j);
            }

        }

    }

    public static String getMove() {
        return " ";//Form: A4 E6
    }

    public static void move(int xStart, int yStart, int xEnd, int yEnd) {
        Figure figureToMoves = board[xStart][yStart];
        char figureType = figureToMoves.type;
        isValidMove(figureType, figureToMoves, xEnd + yEnd + "");


        figureToMoves.hasMoved = true;
    }

    public static void sendBoard() {

    }


    public static void printBoard() {

        System.out.println("\n" +
                "    A  B  C  D  E  F  G  H \n" +
                "   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗\n" +
                " 1 ║ " + board[0][0].type + " ║ " + board[0][1].type + " ║ " + board[0][2].type + " ║ " + board[0][3].type + " ║ " + board[0][4].type + " ║ " + board[0][5].type + " ║ " + board[0][6].type + " ║ " + board[0][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 2 ║ " + board[1][0].type + " ║ " + board[1][1].type + " ║ " + board[1][2].type + " ║ " + board[1][3].type + " ║ " + board[1][4].type + " ║ " + board[1][5].type + " ║ " + board[1][6].type + " ║ " + board[1][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 3 ║ " + board[2][0].type + " ║ " + board[2][1].type + " ║ " + board[2][2].type + " ║ " + board[2][3].type + " ║ " + board[2][4].type + " ║ " + board[2][5].type + " ║ " + board[2][6].type + " ║ " + board[2][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 4 ║ " + board[3][0].type + " ║ " + board[3][1].type + " ║ " + board[3][2].type + " ║ " + board[3][3].type + " ║ " + board[3][4].type + " ║ " + board[3][5].type + " ║ " + board[3][6].type + " ║ " + board[3][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 5 ║ " + board[4][0].type + " ║ " + board[4][1].type + " ║ " + board[4][2].type + " ║ " + board[4][3].type + " ║ " + board[4][4].type + " ║ " + board[4][5].type + " ║ " + board[4][6].type + " ║ " + board[4][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 6 ║ " + board[5][0].type + " ║ " + board[5][1].type + " ║ " + board[5][2].type + " ║ " + board[5][3].type + " ║ " + board[5][4].type + " ║ " + board[5][5].type + " ║ " + board[5][6].type + " ║ " + board[5][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 7 ║ " + board[6][0].type + " ║ " + board[6][1].type + " ║ " + board[6][2].type + " ║ " + board[6][3].type + " ║ " + board[6][4].type + " ║ " + board[6][5].type + " ║ " + board[6][6].type + " ║ " + board[6][7].type + " ║ " + "\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 8 ║ " + board[7][0].type + " ║ " + board[7][1].type + " ║ " + board[7][2].type + " ║ " + board[7][3].type + " ║ " + board[7][4].type + " ║ " + board[7][5].type + " ║ " + board[7][6].type + " ║ " + board[7][7].type + " ║ " + "\n" +
                "   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝\n");
    }
}

