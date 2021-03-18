import java.util.ArrayList;

public class Rooks extends Piece {
    public Rooks(boolean white, int score) {
        super(white, score);
    }

    public Rooks(boolean white) {
        super(white);
    }

    public ArrayList<Move> getLegals (Spot start, Board board) {
        ArrayList<Move> result = new ArrayList<>();
        int i = start.getRow();
        int j = start.getCol();
        for (int z = 0; z < 8 ;z++) {
            if (z != i) {
                Spot end = new Spot( board.getSpot(z, j).getPiece(), z, j);
                if (isValid(start, end, board) == true) {
                    result.add(new Move(start, end, start.getPiece()));
                }
            }
            if (z != j) {
                Spot end = new Spot( board.getSpot(i, z).getPiece(), i, z);
                if (isValid(start, end, board) == true) {
                    result.add(new Move(start, end, start.getPiece()));

                }
            }
        }
        return result;
    }

    public boolean isValid (Spot start, Spot end, Board board) {
        int step_i, step_j;
        if (start.getRow() == end.getRow()) {
            //se misca pe coloana
            step_i = 0;
            if (end.getCol() > start.getCol())
                step_j = 1;
            else step_j = -1;
        }
        else {
            if (start.getCol() == end.getCol()) {
                step_j = 0;
                if (end.getRow() > start.getRow())
                    step_i = 1;
                else step_i = -1;
            }
            else
                return false;
        }

        Spot last = new Spot(start.getPiece(), start.getRow(), start.getCol());
        while (true) {
            int i = last.getRow() + step_i;
            int j = last.getCol() + step_j;
            if (!(i < 0 || j < 0 || j >=8 || i >= 8)){
                last = board.getSpot(i, j);

                //verif daca am ajuns l aend
                if (i == end.getRow() && j == end.getCol()) {
                    if (end.isEmptySpot() != true) {
                        if (end.getPiece().isWhite() == start.getPiece().isWhite()) {
                            return false;
                        } else return true;
                    }
                    else return true;
                }

                if (last.isEmptySpot() != true) {
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
    public boolean isAttack (Spot start, Spot end , Board board) {return false;}

    public boolean isKing () {
        return false;
    }
    public boolean isChessM (Board board, Spot start) {return false;}


}
