public class Server {
    public static Figure[][]  board = new Figure[8][8];
    static boolean checkMate;
    public static void main(String[] args) {
        play();
    }


    public static void play(){
        fillBoard();
        printBoard();
        while (checkMate == false){

        }

    }

    private static void fillBoard() {

        board[0][0] = new Tower();
        board[0][1] = new Knight();
        board[0][2] = new Bishop();
        board[0][3] = new Queen();
        board[0][4] = new King();
        board[0][5] = new Bishop();
        board[0][6] = new Knight();
        board[0][7] = new Tower();

    }

    public static void getMove(){

    }
    public static void move(){

    }
    public static void sendBoard(){

    }
















    public static void printBoard(){
        System.out.println("\n" +
                "    A  B  C  D  E  F  G  H \n"+
                "   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗\n" +
                " 1 ║ "+board[0][0].type+" ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 2 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 3 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 4 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 5 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 6 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 7 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n" +
                " 8 ║   ║   ║   ║   ║   ║   ║   ║   ║\n" +
                "   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝\n");
    }
    }
