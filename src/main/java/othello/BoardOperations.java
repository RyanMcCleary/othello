package othello;

public class BoardOperations {

    public static Square getSquare(long bboardB, long bboardW, int row, int col) {
        Square square = Square.EMPTY;
        long position = indexToLong(row, col);
        if ((bboardB & position) != 0) {
            square = Square.BLACK;
        }
        else if ((bboardW & position) != 0) {
            square = Square.WHITE;
        }
        return square;
    }

    public static long setSquare(long bboardB, long bboardW, int row, int col, Square square) {
        long position = indexToLong(row, col);
        long newBoard = 0;
        if (square == Square.BLACK) {
            newBoard = bboardB | position;
        }
        else if (square == Square.WHITE) {
            newBoard = bboardW | position;
        }
        else {
            if (getSquare(bboardB, bboardW, row, col) == Square.BLACK) {
                newBoard = ~((~bboardB) | position);
            }
            else if (getSquare(bboardB, bboardW, row, col) == Square.WHITE) {
                newBoard = ~((~bboardW) | position);
            }
        }
        return newBoard;
    }

    private static long setSquare(long bboard, long position) {
        return bboard | position;
    }

    private static long indexToLong(int row, int col) {
        return 1L << ((8 * (col - 1)) + (row - 1));
    }

    public static Square[][] longsToBoard(long bboardB, long bboardW) {
        Square[][] board = new Square[8][8];
        for (int i=1; i<=8; i++) {
            for (int j=1; j<=8; j++) {
                long position = indexToLong(i,j);
                if ((bboardB & position) != 0) {
                    board[i][j] = Square.BLACK;
                }
                else if ((bboardW & position) != 0) {
                    board[i][j] = Square.WHITE;
                }
                else {
                    board[i][j] = Square.EMPTY;
                }
            }
        }
        return board;
    }
    
    // bboardP is the current player's bitboard and bboardO is the opponent's bitboard
    public static long getLegalMoves(long bboardP, long bboardO) {
        long legalMoves = 0;
        long position = 1;
        for (int i = 1; i < 64; i++) {
            if (isLegal(bboardP, bboardO, position)) {
                    legalMoves = setSquare(legalMoves, position);
                }
            position = position << 1;
            }
        return legalMoves;
    }

    private static boolean isLegal(long bboardP, long bboardO, long position) {
        boolean legal = false;
        if (isVacant(bboardP, bboardO, position)) {
            if (checkE(bboardP, bboardO, position) | checkNE(bboardP, bboardO, position) | checkN(bboardP, bboardO, position) | checkNW(bboardP, bboardO, position) | checkW(bboardP, bboardO, position) | checkSW(bboardP, bboardO, position) | checkS(bboardP, bboardO, position) | checkSE(bboardP, bboardO, position)) {
                legal = true;
            }
        }
        return legal;
    }
    
    private static boolean isVacant(long bboardP, long bboardO, long position) {
        boolean legal = false;
        if ((position & (bboardP | bboardO)) == 0) {
            legal = true;
        }
        return legal;
    }
    
    private static boolean checkE(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 1;
        long outBounds = 0; // find two right-most columns long
        long inBounds = 0; // find seven leftmost columns long
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift >> 1) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkNE(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 7;
        long outBounds = 0; // find two right-most columns / two top rows long
        long inBounds = 0; // find long with right-most column and top row removed
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift << 7) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkN(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 8;
        long outBounds = 0; // find two top rows long
        long inBounds = 0; // find seven bottom rows long
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift << 8) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkNW(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 9;
        long outBounds = 0; // find two left-most columns / two top rows long
        long inBounds = 0; // find long with left-most column and top row removed
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift << 9) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkW(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 1;
        long outBounds = 0; // find two left-most columns long
        long inBounds = 0; // find seven right-most columns long
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift << 1) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkSW(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 7;
        long outBounds = 0; // find two left-most columns / two bottom rows long
        long inBounds = 0; // find long with left-most column and bottom row removed
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift >> 7) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkS(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 8;
        long outBounds = 0; // find two bottom rows long
        long inBounds = 0; // find seven top tows long
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift >> 8) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    private static boolean checkSE(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 9;
        long outBounds = 0; // find two left-most columns / two bottom rows long
        long inBounds = 0; // find long with left-most column and bottom row removed
        if ((position & outBounds) == 0) {
            while (((shift & bboardO) != 0) & ((shift & inBounds) != 0)) {
                if (((shift >> 9) & bboardP) != 0) {
                    legal = true;
                }
            }
        }
        return legal;
    }

}
