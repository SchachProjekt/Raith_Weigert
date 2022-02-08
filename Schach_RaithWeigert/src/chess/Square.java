package chess;

public abstract class Square {
    public String symbol;
    public String color; //schwarz, weiß oder leer
    public String type;

    public Square(String typeIn){
        type = typeIn;
    }

    String getSymbol(){
        return symbol;
    }
    public String getColor(){
        return color;
    }
    public String getType(){
        return type;
    }

    public abstract boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing);
}

