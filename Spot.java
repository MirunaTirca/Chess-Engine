public class Spot {
    // tine cont de starea unui patrat de pe tabla
    private Piece piece;
    private int row;
    private int col;

    public Spot(Piece piece, int row, int col) {
        this.piece = piece;
        this.row = row;
        this.col = col;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isEmptySpot () {
        if (this.piece == null)
            return true;
        else return false;
    }

    public boolean isFirstMove(){
        if(this.getPiece().isWhite() == true){
            if(this.getRow() == 1 )
                return true;
        }
        else{
            if(this.getRow() == 6 )
                return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Spot{" +
                "piece=" + piece +
                ", row=" + row +
                ", col=" + col +
                '}';

    }
}
