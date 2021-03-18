import java.util.ArrayList;

public abstract class Piece {
    // tine cont de fiecare piesa
    // pe baza ei se declara cate o clasa pentru fiecare tip de piesa
    private boolean white;
    private int score;

    public Piece(boolean white, int score) {
        this.white = white;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Piece(boolean white) {
        this.white = white;
    }


    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public abstract boolean move (Board board, Spot start, Spot end);
    public abstract boolean isValid (Spot start, Spot end, Board board);
    public abstract boolean isAttack (Spot start, Spot end, Board board);
    @Override
    public String toString() {
        return "Piece{" +
                "white=" + white +
                '}';
    }

    public abstract boolean isKing ();
    public abstract boolean isChessM (Board board, Spot start);

    public abstract ArrayList<Move> getLegals (Spot start, Board board);
}
