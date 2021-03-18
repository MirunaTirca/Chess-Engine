import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(boolean white, int score) {
        super(white, score);
    }

    public ArrayList<Move> getLegals (Spot start, Board board) {
        ArrayList<Move> result = new ArrayList<>();
        int i = start.getRow();
        int j = start.getCol();

        int[][] offsets = {
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1} };

        for (int[] o : offsets) {
            if (! (i + o[0] >= 8 || i + o[0] < 0 || j+o[1] >= 8 || j+o[1] < 0)) {
                Spot end = new Spot(board.getSpot(i + o[0], j + o[1]).getPiece(), i + o[0], j + o[1]);
                if (isValid(start, end, board) == true) {
                    result.add(new Move(start, end, start.getPiece()));

                }
            }
        }
        return result;
    }

    public Knight(boolean white) {
        super(white);
    }

    public boolean isValid(Spot start, Spot end, Board board) {
        if (end.isEmptySpot() == false)
            if (end.getPiece().isWhite() == start.getPiece().isWhite())
                return false;
        int x = Math.abs(start.getRow() - end.getRow());
        int y = Math.abs(start.getCol() - end.getCol());
        return x * y == 2;
    }


    //NU O MAI FOLOSESC
    public boolean isAttack(Spot start, Spot end, Board board) {
        return true;
    }

    public boolean move(Board board, Spot start, Spot end) {
        if (isValid(start, end, board) == true) {
            end.setPiece(start.getPiece());
            start.setPiece(null);
            return true; // am putut sa fac miscarea
        }
        return false;
    }


    public boolean isKing () {return false;}
    public boolean isChessM (Board board, Spot start) {return false;}


}
