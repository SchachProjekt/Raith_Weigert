public class Pawn extends Figure{
    public Pawn(int px,int py){
        type = 'B';
        x = px;
        y = py;
        team = 1;
    }
    boolean hasMoved = false;

}
