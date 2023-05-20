package othello;

public class BoardOperations {

    public static long setSquare(long bboardB, long bboardW, int row, int col, Square square) {
        long position = indexToLong(row, col);
        if (square == Square.BLACK) {
            bboardB |= position;
            return bboardB;
        }
        else if (square == Square.WHITE) {
            bboardW |= position;
            return bboardW;
        }
        else {
            if (getSquare(bboardB, bboardW, row, col) == Square.BLACK) {
                bboardB = ~((~bboardB) | position);
                return bboardB;
            }
            else if (getSquare(bboardB, bboardW, row, col) == Square.WHITE) {
                bboardW = ~((~bboardW) | position);
                return bboardW;
            }
        }
        return 0;
    }

    public static long setSquare(long bboard, int row, int col) {
        long position = indexToLong(row, col);
        bboard |= position;
        return bboard;
    }

    public static Square getSquare(long bboardB, long bboardW, int row, int col) {
        long position = indexToLong(row, col);
        if ((bboardB & position) != 0) {
            return Square.BLACK;
        }
        else if ((bboardW & position) != 0) {
            return Square.WHITE;
        }
        else {
            return Square.EMPTY;
        }
    }

    private static long indexToLong(int row, int col) {
        long position = 1L << ((8 * (col - 1)) + (row - 1));
        return position;
    }

    private static boolean isLegal(long bboardB, long bboardW, int row, int col, Player player) {
        return false;
    }
    
    public static long getLegalMoves(long bboardB, long bboardW, Player player) {
        long legalMoves = 0;
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                if (isLegal(bboardB, bboardW, row, col, player)) {
                    if (player == Player.BLACK) {
                        legalMoves = setSquare(legalMoves, bboardW, row, col, Square.BLACK);
                    }
                    else {
                        legalMoves = setSquare(bboardB, legalMoves, row, col, Square.WHITE);
                    }
                }
            }
        }
        return legalMoves;
    }

}
