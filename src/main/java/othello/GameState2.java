package othello;

import java.util.ArrayList;

public class GameState2 {

    // board is two 64 bit integers - first is a bit-board for black, second for white
    // 00...01 is the lower-right corner of the board, 00...10 is the tile to the left of it
    // 11...11 is the upper-left corner, 11...10 is the tile to the right of it
    private Player currentPlayer;
    private long bboardB;
    private long bboardW;

    public GameState2() {
        this.currentPlayer = Player.BLACK;
        this.bboardB = 0;
        this.bboardW = 0;
        this.bboardB = BoardOperations.setSquare(bboardB, bboardW, 3, 4, Square.BLACK);
        this.bboardB = BoardOperations.setSquare(bboardB, bboardW, 4,3,Square.BLACK);
        this.bboardW = BoardOperations.setSquare(bboardB, bboardW, 3, 3, Square.WHITE);
        this.bboardW = BoardOperations.setSquare(bboardB, bboardW, 4,4,Square.WHITE);
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
