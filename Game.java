
public class Game {

	
	Square[][] board;
	Player currentPlayer;
	int boardSize;
	
	
	Game(int n) {
		this.currentPlayer = Player.WHITE;
		this.board = new Square[n][n];
		this.boardSize = n;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				board[row][col] = Square.EMPTY;
			}
		}
		this.board[n / 2 - 1][n / 2 - 1] = this.board[n / 2][n / 2] = Square.WHITE;
		this.board[n / 2 - 1][n / 2] = this.board[n / 2][n / 2 - 1] = Square.BLACK;
	}
	
	Game() {
		this(8);
	}

	// Prints board state
	void printBoard() {
		for(int row = 0; row < this.boardSize; row++) {
			for(int col =0; col < this.boardSize; col++) {
				if(this.board[row][col] == Square.WHITE)
						System.out.print("W");
				else if	(this.board[row][col] == Square.BLACK)
						System.out.print("B");
				else System.out.print("*");
			}
			System.out.println();
		}
	}
	
	Square currentColor() {
		if (this.currentPlayer == Player.BLACK) {
			return Square.BLACK;
		}
		else {
			return Square.WHITE;
		}
	}
	
	Square oppositeColor() {
		if (this.currentPlayer == Player.BLACK) {
			return Square.WHITE;
		}
		else {
			return Square.BLACK;
		}
	}
	
	boolean isValidDirection(int row, int col, int rowDelta, int colDelta) {
		if (!inBounds(row, col) || !inBounds(row + rowDelta, col + colDelta)) {
			return false;
		}
		if (this.board[row + rowDelta][col + colDelta] != oppositeColor()) {
			return false;
		}
		row += rowDelta;
		col += colDelta;
		while (inBounds(row, col) && this.board[row][col] == oppositeColor()) {
			row += rowDelta;
			col += colDelta;
		}
		if (inBounds(row, col) && this.board[row][col] == currentColor()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean isValidMove(int row, int col) {
		for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
			for (int colDelta = -1; colDelta <= 1; colDelta++) {
				if (!(rowDelta == 0 && colDelta == 0) && isValidDirection(row, col, rowDelta, colDelta)) {
					return true;
				}
			}
		}
		return false;
	}
	
	boolean inBounds(int row, int col) {
		return (0 <= row && row < this.boardSize 
				&& 0 <= col && col < this.boardSize);
	}
		
	}





