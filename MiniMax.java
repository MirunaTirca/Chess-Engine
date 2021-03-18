import java.util.ArrayList;

public class MiniMax {

    public boolean gameOver(Board board) {
        return false;
    }
    public Board applyMove (Board board, Move move) {
        if (move != null) {
            Spot start = move.getCurrentLocation();
            Spot end = move.getDestinationLocation();
            Piece p = move.getMovedPiece();
            board.getSpot(end.getRow(), end.getCol()).setPiece(p);
            board.getSpot(start.getRow(), start.getCol()).setPiece(null);
        }
        return board;
    }



    public int evaluate(Board board) {
        int score = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (board.getSpot(i, j).isEmptySpot() == false)
                    score += board.getSpot(i, j).getPiece().getScore();
                    
                }
        return  score;
    }



    public ArrayList<Move> getAllMoves (Board board, Boolean colour) {
        ArrayList<Move> result = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)  {
                if (board.getSpot(i, j).isEmptySpot() == false &&
                    board.getSpot(i, j).getPiece().isWhite() == colour) {
                    result.addAll(board.getSpot(i, j).getPiece().getLegals(board.getSpot(i, j), board));
               }
            }
        return result;
    }



    public Pair<Integer, Move> negamax (Board board, int depth, Boolean colour) {
        MiniMax obj = new MiniMax();

        ArrayList<Move> all_moves = getAllMoves(board, colour);

        if(all_moves.size() != 0){

            Move bestMove = all_moves.get(0);

            if (gameOver(board) || depth == 0 ) {
                Pair<Integer, Move> pair = new Pair<Integer, Move>(evaluate(board), bestMove);
                return pair;
                // ne oprim si evaluam starea curenta
            }
            int max = -9999999;

            // incercam pe rand fiecare care miscare posibila move

            for (Move move : all_moves) {
                Board copy = board.copyBoard(board);
                copy = applyMove(copy, move);// executa move

                if (copy.isCheck(copy, colour) == false){
                    int score = -negamax(copy, depth - 1, !colour).getFirst();
                    if (score > max) {
                        max = score;
                        bestMove = move;
                    }
                }
            }
            return (new Pair<Integer, Move>(max, bestMove));
        }
        return null;
    }
}
