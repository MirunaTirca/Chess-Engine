public class Board {
    // reprezinta tabla de joc
    Spot [][] board = new Spot[8][8];

    public Board(){
        this.resetBoard();
    }

    public Spot getSpot(int row, int col){
        return board[row][col];
    }

    public Board copyBoard(Board board) {
        Board ret = new Board();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                ret.getSpot(i, j).setPiece(board.getSpot(i, j).getPiece());
        return ret;
    }

    public void resetBoard(){
        // Piesele albe:
            board[0][0] = new Spot (new Rooks(true, -50), 0, 0);
            board[0][1] = new Spot (new Knight(true, -40), 0,1);
            board[0][2] = new Spot (new Bishops(true, -30), 0, 2);
            board[0][4] = new Spot (new King(true, -900), 0, 4);
            board[0][3] = new Spot (new Queen(true, -90), 0,3);
            board[0][5] = new Spot (new Bishops(true, -30), 0, 5);
            board[0][6] = new Spot (new Knight(true, -40), 0,6);
            board[0][7] = new Spot (new Rooks(true, -50), 0, 7);

            for (int i = 0; i<8; i++)
                board[1][i] = new Spot (new Pawns(true, -10), 1, i);

            // Piesele negre:
            board[7][0] = new Spot (new Rooks( false, 50), 7, 0);
            board[7][1] = new Spot (new Knight(false, 40), 7,1);
            board[7][2] = new Spot (new Bishops(false, 30), 7, 2);
            board[7][3] = new Spot (new Queen(false, 90), 7, 3);
            board[7][4] = new Spot (new King(false, 900), 7,4);
            board[7][5] = new Spot (new Bishops(false, 30), 7, 5);
            board[7][6] = new Spot (new Knight(false, 40), 7,6);
            board[7][7] = new Spot (new Rooks(false, 50), 7, 7);

            for (int i = 0; i<8; i++)
                board[6][i] = new Spot (new Pawns(false, 10), 6, i);

            for (int i = 2; i<6; i++)
                for(int j = 0; j<8; j++)
                    board[i][j] = new Spot(null, i, j);

    }

    public void updateBoard(){
        for (int i = 0; i<8; i++)
            for (int j = 0; j<8; j++)
                if (this.getSpot(i,j).isEmptySpot() == false)
                    this.getSpot(i,j).getPiece().setScore((this.getSpot(i,j).getPiece().getScore())*(-1)); 
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                 System.out.print(board[i][j].toString());
            System.out.println();
        }
    }

    public boolean isCheck (Board board, boolean colour) {
        //gasim regele
        Spot king = new Spot(null, -1 , -1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getSpot(i, j).isEmptySpot() == false)
                    if (board.getSpot(i, j).getPiece().isKing() &&
                            board.getSpot(i, j).getPiece().isWhite() == colour) {
                        king = board.getSpot(i, j);
                        break;
                    }
            }
        }

        if(king.isEmptySpot() == false){
            //parcurg tabla
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpot(i, j).isEmptySpot() == false &&
                    board.getSpot(i, j).getPiece().isWhite() != colour) {
                        if (board.getSpot(i, j).getPiece().isValid(board.getSpot(i, j), king, board) == true)
                            return true;
                    }
                }
            }
        }
        return  false;
    }
}
