package othello;

public class BoardOperations {

    public static Square getSquare(long bboardB, long bboardW, long position) {
            Square square = Square.EMPTY;
            if ((bboardB & position) != 0L) {
                square = Square.BLACK;
            }
            else if ((bboardW & position) != 0L) {
                square = Square.WHITE;
            }
            return square;
        }

    public static Square getSquare(long bboardB, long bboardW, int row, int col) {
        long position = indexToLong(row, col);
        Square square = getSquare(bboardB, bboardW, position);
        return square;
    }

    public static long setSquare(long bboardB, long bboardW, long position, Square square) {
        long newBoard = 0L;
        if (square == Square.BLACK) {
            newBoard = bboardB | position;
        }
        else if (square == Square.WHITE) {
            newBoard = bboardW | position;
        }
        else {
            if (getSquare(bboardB, bboardW, position) == Square.BLACK) {
                newBoard = ~((~bboardB) | position);
            }
            else if (getSquare(bboardB, bboardW, position) == Square.WHITE) {
                newBoard = ~((~bboardW) | position);
            }
        }
        return newBoard;
    }

    public static long setSquare(long bboardB, long bboardW, int row, int col, Square square) {
        long position = indexToLong(row, col);
        long newBoard = setSquare(bboardB, bboardW, position, square);
        return newBoard;
    }

    public static long setSquare(long bboard, long position) {
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
                if ((bboardB & position) != 0L) {
                    board[i][j] = Square.BLACK;
                }
                else if ((bboardW & position) != 0L) {
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
        long legalMoves = 0L;
        long position = 1L;
        for (int i = 1; i < 64; i++) {
            if (isLegal(bboardP, bboardO, position)) {
                    legalMoves = setSquare(legalMoves, position);
            }
            position = position << 1;
        }
        return legalMoves;
    }

    public static boolean isLegal(long bboardP, long bboardO, long position) {
        boolean legal = false;
        if (isVacant(bboardP, bboardO, position)) {
            if (checkE(bboardP, bboardO, position) | checkNE(bboardP, bboardO, position) | checkN(bboardP, bboardO, position) | checkNW(bboardP, bboardO, position) | checkW(bboardP, bboardO, position) | checkSW(bboardP, bboardO, position) | checkS(bboardP, bboardO, position) | checkSE(bboardP, bboardO, position)) {
                legal = true;
            }
        }
        return legal;
    }
    
    public static boolean isVacant(long bboardP, long bboardO, long position) {
        boolean legal = false;
        if ((position & (bboardP | bboardO)) == 0L) {
            legal = true;
        }
        return legal;
    }
    
    public static boolean checkE(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 1;
        long outBounds = 0x0303030303030303L; // two right-most columns 1s
        long inBounds = 0xFEFEFEFEFEFEFEFEL; // right-most column 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift >> 1;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkNE(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 7;
        long outBounds = 0x0303030303030000L; // two right-most columns / two top rows 1s
        long inBounds = 0x00FEFEFEFEFEFEFEL; // right-most column and top row 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift << 7;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkN(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 8;
        long outBounds = 0xFFFF000000000000L; // two top rows 1s
        long inBounds = 0x00FFFFFFFFFFFFFFL; // top row 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift << 8;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkNW(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 9;
        long outBounds = 0xFFFFC0C0C0C0C0C0L; // two left-most columns / two top rows 1s
        long inBounds = 0x007F7F7F7F7F7F7FL; // left-most column / top row 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift << 9;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkW(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position << 1;
        long outBounds = 0xC0C0C0C0C0C0C0C0L; // two left-most columns 1s
        long inBounds = 0x7F7F7F7F7F7F7F7FL; //  left-most column 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift << 1;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkSW(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 7;
        long outBounds = 0xC0C0C0C0C0C0FFFFL; // two left-most columns / two bottom rows 1s
        long inBounds = 0x7F7F7F7F7F7F7F00L; // left-most column / bottom row 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift >> 7;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkS(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 8;
        long outBounds = 0x000000000000FFFFL; // two bottom rows 1s
        long inBounds = 0xFFFFFFFFFFFFFF00L; // bottom row 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift >> 8;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static boolean checkSE(long bboardP, long bboardO, long position) {
        boolean legal = false;
        long shift = position >> 9;
        long outBounds = 0x030303030303FFFFL; // two right-most columns / two bottom rows 1s
        long inBounds = 0xFEFEFEFEFEFEFE00L; // right-most column / bottom row 0s
        if ((position & outBounds) == 0L) {
            while ((shift & bboardO & inBounds) != 0L) {
                shift = shift >> 9;
                if ((shift & bboardP) != 0L) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public static GameResult checkStatus(long bboardB, long bboardW) {
        GameResult status = GameResult.IN_PROGRESS;
        long legalMoves = getLegalMoves(bboardB, bboardW) | getLegalMoves(bboardW, bboardB);
        if (legalMoves == 0L) {
            if (Long.bitCount(bboardB) > Long.bitCount(bboardW)) {
                status = GameResult.BLACK_WIN;
            }
            else if (Long.bitCount(bboardB) < Long.bitCount(bboardW)) {
                status = GameResult.WHITE_WIN;
            }
            else {
                status = GameResult.TIE;
            }
        }
        return status;
    }

}
