import java.util.Locale;

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

            if (isValidMove('2')) {
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
                move(movesAsCharArray[1], yStart, movesAsCharArray[4], yEnd);
                if (player == 1) {
                    player = 2;
                } else if (player == 2) {
                    player = 1;
                }
            } else {
                //An Client: Yo das ged so nicht
            }


        }
    }

    private static void isValidMove(char figureType) {
        switch (figureType) {
            case 'B':
                howToMovePawn();
                break;

            case 'T':
                howToMoveRook();
                break;

            case 'L':
                howToMoveBishop();
                break;

            case 'S':
                howToMoveKnight();
                break;

            case 'D':
                howToMoveQueen();
                break;

            case 'K':
                howToMoveKing();
                break;


        }


    }

    private static void chooseName() {
        System.out.println("Bitte geben Sie einen Benutzernamen ein");
    }

    private static void fillBoard() {

        board[0][0] = new Rook(0, 0);
        board[0][1] = new Knight(0, 1);
        board[0][2] = new Bishop(0, 2);
        board[0][3] = new Queen(0, 3);
        board[0][4] = new King(0, 4);
        board[0][5] = new Bishop(0, 5);
        board[0][6] = new Knight(0, 6);
        board[0][7] = new Rook(0, 7);

        board[1][0] = new Pawn(1, 0);
        board[1][1] = new Pawn(1, 1);
        board[1][2] = new Pawn(1, 2);
        board[1][3] = new Pawn(1, 3);
        board[1][4] = new Pawn(1, 4);
        board[1][5] = new Pawn(1, 5);
        board[1][6] = new Pawn(1, 6);
        board[1][7] = new Pawn(1, 7);

        board[7][0] = new Rook(7, 0);
        board[7][1] = new Knight(7, 1);
        board[7][2] = new Bishop(7, 2);
        board[7][3] = new Queen(7, 3);
        board[7][4] = new King(7, 4);
        board[7][5] = new Bishop(7, 5);
        board[7][6] = new Knight(7, 6);
        board[7][7] = new Rook(7, 7);

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
                board[i][j] = new Empty(i,j);
            }

        }

    }

    public static String getMove() {
        return " ";//Form: A4 E6
    }

    public static void move(int xStart, int yStart, int xEnd, int yEnd) {
        Figure figureToMoves = board[xStart][yStart];
        char figureType = figureToMoves.type;
        isValidMove(figureType);
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
                " 8 ║ " + board[7][0].type + " ║ " + board[7][1].type + " ║ " + board[7][2].type + " ║ " + board[7][3].type + " ║ " + board[7][4].type + " ║ " + board[7][5].type + " ║ " + board[7][6].type + " ║ " + board[7][7].type + " ║ " +"\n"+
                "   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝\n");
    }
}

