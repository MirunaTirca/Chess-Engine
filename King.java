import java.util.ArrayList;

public class King extends Piece {
    public King(boolean white, int score) {
        super(white, score);
    }

    public King(boolean white) {
        super(white);
    }

    public ArrayList<Move> getLegals (Spot start, Board board) {
        ArrayList<Move> result = new ArrayList<>();
        int i = start.getRow();
        int j = start.getCol();

        int[][] offsets = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
                {1, 1},
                {-1, 1},
                {-1, -1},
                {1, -1}
        };
        for (int[] o : offsets) {
            if (! (i + o[0] >= 8 || i + o[0] < 0 || j+o[1] >= 8 || j+o[1] < 0)) {
                Spot end = new Spot(board.getSpot(i + o[0], j + o[1]).getPiece(), i + o[0], j + o[1]);
                if (isValid(start, end, board) == true)
                    result.add(new Move(start, end, start.getPiece()));
            }
        }
        return result;
    }

    public boolean isChessM (Board board, Spot start) {
        if (getLegals(start, board).isEmpty() == true)
            return true;
        else return false;
    }

    public boolean isValid (Spot start, Spot end, Board board) {


        if (Math.abs (start.getRow() - end.getRow()) <= 1 && Math.abs (start.getCol() - end.getCol()) <= 1) {
            if (end.isEmptySpot() == false) {
                if (start.getPiece().isWhite() == end.getPiece().isWhite())
                    return false;
                else return true;
            }
            else return true;
        }
        return false;
    }

    public boolean move (Board board,Spot start, Spot end) {
        if (isValid(start, end, board) == false) {
            //resign sau ceva -?????
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
        return true;
    }

}
