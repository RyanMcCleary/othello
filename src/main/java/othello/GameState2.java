package othello;

import java.util.ArrayList;

public class GameState2 {

    // board is two 64 bit integers - first is a bit-board for black, second for white
    // 00...01 is the lower-right corner of the board, 00...10 is the tile to the left of it
    // 11...11 is the upper-left corner, 11...10 is the tile to the right of it
    private Player currentPlayer;
    private long[] board;
    private ArrayList legalMoves;

    public GameState2() {
        this.currentPlayer = Player.BLACK;
        this.board = new long[2];
        setSquare(3,4,Square.BLACK);
        setSquare(4,3,Square.BLACK);
        setSquare(3,3,Square.WHITE);
        setSquare(4,4,Square.WHITE);
        this.legalMoves = new long[]{};
        updateLegalMoves();
    }

    public void setSquare(int row, int col, Square square) {
        long position = indexToLong(row, col);
        if (square == Square.BLACK) {
            board[0] |= position;
        }
        else if (square == Square.WHITE) {
            board[1] |= position;
        }
        else {
            if (getSquare(row, col) == Square.BLACK) {
                board[0] = ~((~board[0]) | position);
            }
            else if (getSquare(row, col) == Square.WHITE) {
                board[1] = ~((~board[1]) | position);
            }
        }
        return;
    }

    public Square getSquare(int row, int col) {
        long position = indexToLong(row, col);
        if ((board[0] & position) != 0) {
            return Square.BLACK;
        }
        else if ((board[1] & position) != 0) {
            return Square.WHITE;
        }
        else {
            return Square.EMPTY;
        }
    }

    private long indexToLong(int row, int col) {
        long position = 1L << ((8 * (col - 1)) + (row - 1));
        return position;
    }

    public void switchPlayer() {
        if (currentPlayer == Player.BLACK) {
            currentPlayer = Player.WHITE;
        }
        else {
            currentPlayer = Player.BLACK;
        }
    }

    private boolean isLegal(int row, int col, Player player) {
        return false;
    }
    

    private void updateLegalMoves() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                if (isLegal(row, col, currentPlayer)) {
                    legalMoves.append(indexToLong(row, col));
                }
            }
        }
    }
}
