import java.util.ArrayList;
import java.util.Random;
import java.util.Optional;

public class Game {
	private Square[][] board;
	private Player currentPlayer;
	private int boardSize;
	
	
	public Game(int n) {
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
	
	public Game() {
		this(8);
	}
	
	void setSquare(int row, int col, Square s) {
		this.board[row][col] = s;
	}
	
	Square getSquare(int row, int col) {
		return this.board[row][col];
	}

	boolean flipPiecesInDirection(int row, int col, int rowDelta, int colDelta) {
		if (!isValidDirection(row, col, rowDelta, colDelta)) {
			return false;
		}
		System.out.printf("flipPiecesInDirection(%d, %d, %d, %d)\n", row, col, rowDelta, colDelta);
		row += rowDelta;
		col += colDelta;
		while (inBounds(row, col) && this.board[row][col] == oppositeColor()) {
			setSquare(row, col, currentColor());
			row += rowDelta;
			col += colDelta;
		}
		return true;
	}	
	
	Player switchPlayer() {
		if (this.currentPlayer == Player.WHITE)
			this.currentPlayer = Player.BLACK;
		else {
			this.currentPlayer = Player.WHITE;
		}
		return currentPlayer;
	}
	
	// Prints board state
	public void printBoard() {
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
	
	public Square currentColor() {
		if (this.currentPlayer == Player.BLACK) {
			return Square.BLACK;
		}
		else {
			return Square.WHITE;
		}
	}
	
	public Square oppositeColor() {
		if (this.currentPlayer == Player.BLACK) {
			return Square.WHITE;
		}
		else {
			return Square.BLACK;
		}
	}
	
	public boolean isValidDirection(int row, int col, int rowDelta, int colDelta) {
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
	
	public boolean isValidMove(int row, int col) {
		for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
			for (int colDelta = -1; colDelta <= 1; colDelta++) {
				if (!(rowDelta == 0 && colDelta == 0) && isValidDirection(row, col, rowDelta, colDelta)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean inBounds(int row, int col) {
		return (0 <= row && row < this.boardSize 
				&& 0 <= col && col < this.boardSize);
	}

	boolean makeMove(int row, int col) {
		boolean validMove = false;
		for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
			for (int colDelta = -1; colDelta <= 1; colDelta++) {
				if (!(rowDelta == 0 && colDelta == 0)) {
					validMove = validMove || flipPiecesInDirection(row, col, rowDelta, colDelta);
				}
			}
		}
		if (validMove) {
			setSquare(row, col, currentColor());
		}
		return validMove;
	}	
	
	/**
	 *  This function returns an ArrayList of Pair objects corresponding
	 *  to all of the valid moves.
	 */
	public ArrayList<SquareIndex> movesList()  {
		ArrayList<SquareIndex> moves = new ArrayList<>();	
		for(int i = 0; i < boardSize; i++)  {
			for(int j = 0; j < boardSize; j++)  {
				if(isValidMove(i, j))
					moves.add(new SquareIndex(i, j));
			}
			
		}
		return moves;
	}

	public Optional<Player> checkWin() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
	
	/** 
	 *  This function calls movesList to obtain a list of all possible moves.
	 *  Then, we randomly pick moves from the array. We do this for both the computer and 
	 *  the player until the game is over. The winning player is returned. 
	 */
	/*public takeTurn(Player player)  {
		
	}*/
	/*public Player playRandomGame()  {
	
	}*/
}
