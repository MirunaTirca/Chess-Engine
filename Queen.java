import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean white, int score) {
        super(white, score);
    }

    public Queen(boolean white) {
        super(white);
    }

    public ArrayList<Move> getLegals (Spot start, Board board) {

        int i = start.getRow();
        int j = start.getCol();

        Rooks r = new Rooks(start.getPiece().isWhite());
        Bishops b = new Bishops(start.getPiece().isWhite());

        ArrayList<Move> res1 = r.getLegals(start, board);
        ArrayList<Move> res2 = b.getLegals(start, board);
        res1.addAll(res2);
        return res1;
    }

    public boolean isValid (Spot start, Spot end, Board board) {
        Rooks r = new Rooks(start.getPiece().isWhite());
        Bishops b = new Bishops(start.getPiece().isWhite());
        return r.isValid(start, end, board) || b.isValid(start, end, board);
    }

    public boolean move (Board board, Spot start, Spot end) {
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

    public boolean isKing () {return false;}
    public boolean isChessM (Board board, Spot start) {return false;}



}
