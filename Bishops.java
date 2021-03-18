import java.util.ArrayList;

public class Bishops extends Piece {

    public Bishops(boolean white, int score) {
        super(white, score);
    }

    public Bishops(boolean white) {
        super(white);
    }
    public ArrayList<Move> getLegals (Spot start, Board board) {
        ArrayList<Move> result = new ArrayList<>();
        int i = start.getRow();
        int j = start.getCol();

        for (int z = i+1, t = j+1; z < 8 && t < 8; z++,t++) {
            Spot end = new Spot (board.getSpot(z,t).getPiece(), z , t);
            if (isValid(start, end, board) == true)
                result.add(new Move(start, end, start.getPiece()));
        }
        for (int z = i-1, t = j-1; z >= 0 && t >= 0; z--,t--) {
            Spot end = new Spot (board.getSpot(z,t).getPiece(), z , t);
            if (isValid(start, end, board) == true)
                result.add(new Move(start, end, start.getPiece()));
        }

        for (int z = i-1, t = j+1; z >= 0 && t < 8; z--,t++) {
            Spot end = new Spot (board.getSpot(z,t).getPiece(), z , t);
            if (isValid(start, end, board) == true)
                result.add(new Move(start, end, start.getPiece()));
        }

        for (int z = i+1, t = j-1; z < 8 && t >= 0; z++,t--) {
            Spot end = new Spot (board.getSpot(z,t).getPiece(), z , t);
            if (isValid(start, end, board) == true)
                result.add(new Move(start, end, start.getPiece()));
        }

        return result;
    }
    public boolean isValid (Spot start, Spot end, Board board) {
        if (Math.abs (start.getRow() - end.getRow()) != Math.abs(start.getCol() - end.getCol())){
            return false;
        }
        else {
            int step_i, step_j;
            if (end.getRow() > start.getRow()){
                step_i = 1;
                if (end.getCol() > start.getCol())
                    step_j = 1;
                else step_j = -1;

            }
            else {
                step_i = -1;
                if (end.getCol() > start.getCol())
                    step_j = 1;
                else step_j = -1;
            }
            Spot last = start;
            while (true) {
                int i = last.getRow() + step_i;
                int j = last.getCol() + step_j;
                last = board.getSpot(i, j);
                if (i == end.getRow() && j == end.getCol()) {
                    if (end.isEmptySpot() != true) {
                        if (end.getPiece().isWhite() == start.getPiece().isWhite()) {
                            return false;
                        } else return true;
                    }
                    else return true;
                } else {
                    //verificare spoturile pe unde trece
                    if (board.getSpot(i, j).isEmptySpot() == false)
                        return false;
                }
            }
        }

    }

    public boolean move (Board board, Spot start, Spot end) {
        if (isValid(start, end, board) == false) {
            return false;
        }
        else {
            end.setPiece(start.getPiece());
            start.setPiece(null);
            return true; // am putut sa fac miscarea
        }
    }
    public boolean isAttack (Spot start, Spot end, Board board) {return false;}
    public boolean isKing () {
        return false;
    }
    public boolean isChessM (Board board, Spot start) {return false;}



}
