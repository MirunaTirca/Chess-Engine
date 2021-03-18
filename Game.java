import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String writeBuffer(int i1, int i2, int j1, int j2) {
        char I1 =(char) (i1 + 49);
        char I2 = (char) (i2 + 49);
        char J1 = (char) (j1 + 97);
        char J2 = (char) (j2 + 97);
        String buffer = "move ";
        buffer += J1;
        buffer += I1;
        buffer += J2;
        buffer += I2;
        buffer += "\n";
        return buffer;
    }
    public static void main(String args[]) throws IOException {
        Board b = new Board();
        b.resetBoard();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bo = new BufferedWriter(new OutputStreamWriter(System.out));

        Game g = new Game();

        boolean okForce = false; // nu suntem in modul force
        boolean okColour = false;
        int k =0;

        while (true)  {
            String cmd = br.readLine();
            if (cmd.equals("protover 2")) {
                bo.write("feature sigint=0 sigterm=0 san=0\n");
                bo.flush();
            }

            if (cmd.equals("quit")) {
                System.exit(0);
            }

            if(cmd.equals("new")) {
                b.resetBoard();

            }

            if (cmd.equals("black") && k == 0) {
                okColour = true;
                b.updateBoard();
                k = 1;

                // raspuns bot
                MiniMax obj = new MiniMax();
                Move next = obj.negamax(b, 4, okColour).getSecond();

                obj.applyMove(b,next);
                bo.write(g.writeBuffer(next.currentLocation.getRow(),next.destinationLocation.getRow(),
                        next.currentLocation.getCol(), next.destinationLocation.getCol()));
                bo.flush();
           }

            if (cmd.length() > 1 && cmd.charAt(1) <= 57) {
                int start_j = cmd.charAt(0) - 97;
                int start_i = cmd.charAt(1) - 49;
                int end_j = cmd.charAt(2) - 97;
                int end_i = cmd.charAt(3) - 49;

                k = 1;

                // mutare xbord
                b.getSpot(end_i, end_j).setPiece(b.getSpot(start_i, start_j).getPiece());
                b.getSpot(start_i, start_j).setPiece(null);

                if (cmd.length() > 4 && cmd.charAt(4) == 'q') {
                    if (!(b.getSpot(end_i, end_j).isEmptySpot()) && Math.abs(b.getSpot(end_i, end_j).getPiece().getScore()) == 10){
                       // pawn promotion
                        boolean w = b.getSpot(end_i, end_j).getPiece().isWhite();
                        if (b.getSpot(end_i, end_j).getPiece().getScore() < 0)
                            b.getSpot(end_i, end_j).setPiece(new Queen(w, -90));
                        else b.getSpot(end_i, end_j).setPiece(new Queen(w, 90));
                   }
                }
                // mutare bot
                // raspuns bot
                MiniMax obj = new MiniMax();
                if(obj.negamax(b, 4, okColour).getSecond() != null){
                    Move next = obj.negamax(b, 4, okColour).getSecond();

                    obj.applyMove(b,next);
                    bo.write(g.writeBuffer(next.currentLocation.getRow(),next.destinationLocation.getRow(),
                                    next.currentLocation.getCol(), next.destinationLocation.getCol()));
                    bo.flush();
                }
            }
            bo.flush();
        }
    }
}