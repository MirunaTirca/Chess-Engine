import java.util.ArrayList;

public class Pawns extends Piece {

    public Pawns(boolean white, int score) {
        super(white, score);
    }
    public Pawns(boolean white) {
        super(white);
    }

    public ArrayList<Move> getLegals (Spot start, Board board) {
        ArrayList<Move> result = new ArrayList<>();

        int i = start.getRow();
        int j = start.getCol();

        if (start.getPiece().isWhite() == true) {
            if (i == 1) {
                Spot end = new Spot(board.getSpot(i+2, j).getPiece(), i+2, j);
                if (isValid(start, end, board) == true)
                    result.add(new Move(start, end, board.getSpot(i, j).getPiece()));
            }
            if (!(i+1 <0 || i+1 >= 8)) {
                Spot end1 = new Spot(board.getSpot(i + 1, j).getPiece(), i + 1, j);
                if (isValid(start, end1, board) == true)
                    result.add(new Move(start, end1, board.getSpot(i, j).getPiece()));
            }
            if (!(i+1 >= 8 || i+1 < 0 || j+1 >= 8 || j+1 < 0)) {
                Spot end2 = new Spot(board.getSpot(i + 1, j + 1).getPiece(), i + 1, j + 1);
                if (isAttack(start, end2, board) == true)
                    result.add(new Move(start, end2, board.getSpot(i, j).getPiece()));
            }
            if (!(i+1 < 0 || i+1 >= 8 || j-1 < 0 || j-1 >= 8)) {
                Spot end3 = new Spot(board.getSpot(i + 1, j - 1).getPiece(), i + 1, j - 1);
                if (isAttack(start, end3, board) == true)
                    result.add(new Move(start, end3, board.getSpot(i, j).getPiece()));
            }
            return result;
        }
        else {
            if (i == 6) {
                Spot end = new Spot(board.getSpot(i-2, j).getPiece(), i-2, j);
                if (isValid(start, end, board) == true)
                    result.add(new Move(start, end, board.getSpot(i, j).getPiece()));
            }
            if  (!(i-1 < 0 || i-1 >= 8)) {
                Spot end1 = new Spot(board.getSpot(i - 1, j).getPiece(), i - 1, j);
                if (isValid(start, end1, board) == true)
                    result.add(new Move(start, end1, board.getSpot(i, j).getPiece()));
            }
            if (!(i-1 < 0 || i-1 >= 8 || j+1 >= 8 || j+1 < 0)) {
                Spot end2 = new Spot(board.getSpot(i - 1, j + 1).getPiece(), i - 1, j + 1);
                if (isAttack(start, end2, board) == true)
                    result.add(new Move(start, end2, board.getSpot(i, j).getPiece()));
            }
            if (!(i-1<0 || i-1 >= 8 || j-1 < 0 || j-1 >= 8)) {
                Spot end3 = new Spot(board.getSpot(i - 1, j - 1).getPiece(), i - 1, j - 1);
                if (isAttack(start, end3, board) == true)
                    result.add(new Move(start, end3, board.getSpot(i, j).getPiece()));
            }
            return result;
        }

    }

    public boolean isAttack (Spot start, Spot end, Board board) {
        if (end.getRow() < 0 || end.getRow() > 7 || end.getCol() < 0 || end.getCol() > 7)
            return false;
        int step;
        if (start.getPiece().isWhite() == true)
            step = 1;
        else step = -1;

        if (end.isEmptySpot() == true)
            return false;
        if (start.getPiece().isWhite() == end.getPiece().isWhite())
            return false;
        else {
            if ((end.getRow() - start.getRow()) != step)
                return false;
            else {
                if (Math.abs (start.getCol()- end.getCol()) != 1)
                    return false;
                else
                    return true;
            }
        }
    }

    public boolean isValid (Spot start, Spot end, Board board) {
        if (end.getRow() < 0 || end.getRow() > 7 || end.getCol() < 0 || end.getCol() > 7)
            return false;

        if (start.getCol() == end.getCol()) {
            if (start.getPiece().isWhite() == true) {
                if (start.getRow() == 1) {
                    if (board.getSpot(start.getRow()+1, start.getCol()).isEmptySpot() && (start.getRow() - end.getRow()) == 2) {
                        return end.isEmptySpot();
                    }
                    if((end.getRow() - start.getRow()) == 1)
                        return end.isEmptySpot();
                    return false;
                }
                else {
                    if ((end.getRow() - start.getRow()) == 1) {
                        return end.isEmptySpot();
                    }
                    return false;
                }
            }

            //black
            if (start.getRow() == 6) {
                if (board.getSpot(start.getRow()-1, start.getCol()).isEmptySpot() && (start.getRow() - end.getRow()) == 2) {
                    return end.isEmptySpot();
                }

                if((start.getRow() - end.getRow()) == 1)
                    return end.isEmptySpot();
                return false;

            }
            else {
                if ((start.getRow() - end.getRow()) == 1) {
                    return end.isEmptySpot();
                }
                return false;
            }
        }
        return false;

    }


    public boolean move (Board board, Spot start, Spot end) {
        try {
            if (start.getPiece().isAttack(start, end, board) == true) {
                end.setPiece(start.getPiece());
                start.setPiece(null);
                return true; // am putut sa fac miscarea
            }
            else {
                if (start.getPiece().isValid(start, end, board) == false) {
                    System.out.println("resign");
                    return false; // trb sa ies
                }
                else {
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    return true; // am putut sa fac miscarea
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }
    public boolean isKing () {return false;}
    public boolean isChessM (Board board, Spot start) {return false;}



}