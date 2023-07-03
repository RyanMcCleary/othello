package othello;

// import java.util.ArrayList;

public class GameState2 {

    // 00...01 is the lower-right corner of the board, 00...10 is the tile to the left of it
    // 10...00 is the upper-left corner, 01...00 is the tile to the right of it
    public long bboardB;
    public long bboardW;

    public GameState2() {
        this.bboardB = 0x0000000810000000L;
        this.bboardW = 0x0000001008000000L;
    }

    public Player switchPlayer(Player currentPlayer) {
        Player newPlayer;
        if (currentPlayer == Player.BLACK) {
            newPlayer = Player.WHITE;
        }
        else {
            newPlayer = Player.BLACK;
        }
        return newPlayer;
    }

    public boolean flipRay(long position, int vert, int hori, Player currentPlayer) {
        boolean moveMade = false;
        long shift = 0L;
        if (currentPlayer == Player.BLACK) {
            if ((vert == -1) && (hori == -1) && BoardOperations.checkSW(bboardB, bboardW, position)) {
                shift = position >> 7;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift >> 7;
                }
                moveMade = true;
            }
            else if ((vert == -1) && (hori == 0) && BoardOperations.checkS(bboardB, bboardW, position)) {
                shift = position >> 8;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift >> 8;
                }
                moveMade = true;
            }
            else if ((vert == -1) && (hori == 1) && BoardOperations.checkSE(bboardB, bboardW, position)) {
                shift = position >> 9;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift >> 9;
                }
                moveMade = true;
            }
            else if ((vert == 0) && (hori == -1) && BoardOperations.checkW(bboardB, bboardW, position)) {
                shift = position << 1;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift << 1;
                }
                moveMade = true;
            }
            else if ((vert == 0) && (hori == 1) && BoardOperations.checkE(bboardB, bboardW, position)) {
                shift = position >> 1;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift >> 1;
                }
                moveMade = true;
            }
            else if ((vert == 1) && (hori == -1) && BoardOperations.checkNW(bboardB, bboardW, position)) {
                shift = position << 9;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift << 9;
                }
                moveMade = true;
            }
            else if ((vert == 1) && (hori == 0) && BoardOperations.checkN(bboardB, bboardW, position)) {
                shift = position << 8;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift << 8;
                }
                moveMade = true;
            }
            else if ((vert == 1) && (hori == 1) && BoardOperations.checkNE(bboardB, bboardW, position)) {
                shift = position << 7;
                while ((shift & bboardB) == 0L) {
                    bboardB = BoardOperations.setSquare(bboardB, shift);
                    bboardW = BoardOperations.setSquare(bboardB, bboardW, shift, Square.EMPTY);
                    shift = shift << 7;
                }
                moveMade = true;
            }
            if (moveMade == true) {
                bboardB = BoardOperations.setSquare(bboardB, position);
            }
        }
        else {
            if ((vert == -1) && (hori == -1) && BoardOperations.checkSW(bboardW, bboardB, position)) {
                shift = position >> 7;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift >> 7;
                }
                moveMade = true;
            }
            else if ((vert == -1) && (hori == 0) && BoardOperations.checkS(bboardW, bboardB, position)) {
                shift = position >> 8;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift >> 8;
                }
                moveMade = true;
            }
            else if ((vert == -1) && (hori == 1) && BoardOperations.checkSE(bboardW, bboardB, position)) {
                shift = position >> 9;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift >> 9;
                }
                moveMade = true;
            }
            else if ((vert == 0) && (hori == -1) && BoardOperations.checkW(bboardW, bboardB, position)) {
                shift = position << 1;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift << 1;
                }
                moveMade = true;
            }
            else if ((vert == 0) && (hori == 1) && BoardOperations.checkE(bboardW, bboardB, position)) {
                shift = position >> 1;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift >> 1;
                }
                moveMade = true;
            }
            else if ((vert == 1) && (hori == -1) && BoardOperations.checkNW(bboardW, bboardB, position)) {
                shift = position << 9;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift << 9;
                }
                moveMade = true;
            }
            else if ((vert == 1) && (hori == 0) && BoardOperations.checkN(bboardW, bboardB, position)) {
                shift = position << 8;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift << 8;
                }
                moveMade = true;
            }
            else if ((vert == 1) && (hori == 1) && BoardOperations.checkNE(bboardW, bboardB, position)) {
                shift = position << 7;
                while ((shift & bboardW) == 0L) {
                    bboardW = BoardOperations.setSquare(bboardW, shift);
                    bboardB = BoardOperations.setSquare(bboardW, bboardB, shift, Square.EMPTY);
                    shift = shift << 7;
                }
                moveMade = true;
            }
            if (moveMade == true) {
                bboardW = BoardOperations.setSquare(bboardW, position);
            }
        }
        return moveMade;
    }

    public Player makeMove(long position, Player currentPlayer) {
        Player player = currentPlayer;
        boolean moveMade = false;
        if (BoardOperations.isVacant(bboardB, bboardW, position)) {
            for (int vert = -1; vert < 1; vert++) {
                for (int hori = -1; hori < 1; hori++) {
                    if (flipRay(position, vert, hori, player)) {
                        moveMade = true;
                    }
                }
            }
            if (moveMade) {
                if ((currentPlayer == Player.BLACK) & (BoardOperations.getLegalMoves(bboardW, bboardB) != 0L)) {
                    player = switchPlayer(currentPlayer);
                }
                else if ((currentPlayer == Player.WHITE) & (BoardOperations.getLegalMoves(bboardB, bboardW) != 0L)) {
                    player = switchPlayer(currentPlayer);
                }
            }
        }
        return player;
    }

    public boolean makeMove2(long position, Player currentPlayer) {
        Player player = currentPlayer;
        boolean moveMade = false;
        if (BoardOperations.isVacant(bboardB, bboardW, position)) {
            for (int vert = -1; vert < 1; vert++) {
                for (int hori = -1; hori < 1; hori++) {
                    if (flipRay(position, vert, hori, player)) {
                        moveMade = true;
                    }
                }
            }
            if (moveMade) {
                if ((currentPlayer == Player.BLACK) & (BoardOperations.getLegalMoves(bboardW, bboardB) != 0L)) {
                    player = switchPlayer(currentPlayer);
                }
                else if ((currentPlayer == Player.WHITE) & (BoardOperations.getLegalMoves(bboardB, bboardW) != 0L)) {
                    player = switchPlayer(currentPlayer);
                }
            }
        }
        return moveMade;
    }
}
