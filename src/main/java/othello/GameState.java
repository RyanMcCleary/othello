package othello;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.lang.String;
import java.io.FileNotFoundException;
// import java.util.Optional;

public class GameState {
	private Square[][] board;
	private Player currentPlayer;
	
	public GameState() {
		this.currentPlayer = Player.BLACK;
		this.board = new Square[8][8];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = Square.EMPTY;
			}
		}
		this.board[3][3] = this.board[4][4] = Square.WHITE;
		this.board[3][4] = this.board[4][3] = Square.BLACK;
	}
	
	public GameState(String filename) throws FileNotFoundException {
		this.currentPlayer = Player.BLACK;
		this.board = new Square[8][8];
		Scanner scanner = new Scanner(new File(filename));
		String line = "";
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			int i=0;
			for (int j=0; j<line.length(); j++) { 
				if (line.charAt(j) == '*') {
					this.board[i][j] = Square.EMPTY;
				}
				else if (line.charAt(j) == 'W') {
					this.board[i][j] = Square.WHITE;
				}
				else {
					this.board[i][j] = Square.BLACK;
				}
			} 
			i++;
		} 
	}
	
	public void setSquare(int row, int col, Square s) {
		this.board[row][col] = s;
	}
	
	public Square getSquare(int row, int col) {
		return this.board[row][col];
	}
	
	public Player switchPlayer() {
		if (this.currentPlayer == Player.WHITE) {
			this.currentPlayer = Player.BLACK;
		}
		else {
			this.currentPlayer = Player.WHITE;
		}
		return currentPlayer;
	}

	public Square getCurrentColor() {
		if (this.currentPlayer == Player.BLACK) {
			return Square.BLACK;
		}
		else {
			return Square.WHITE;
		}
	}
	
	public Square getOppositeColor() {
		if (this.currentPlayer == Player.BLACK) {
			return Square.WHITE;
		}
		else {
			return Square.BLACK;
		}
	}

	/* 
	 * Input a square's location and a direction, flip pieces in that direction if possible and return true, otherwise return false
	 * rowDelta == 1 && colDelta == 0 implies the direction is right
	 * rowDelta == -1 && colDelta == -1 implies the direction is up-left, etc. 
	 */
	public boolean flipPiecesInDirection(int row, int col, int rowDelta, int colDelta) {
		if (!isValidDirection(row, col, rowDelta, colDelta)) {
			return false;
		}
		System.out.printf("flipPiecesInDirection(%d, %d, %d, %d)\n", row, col, rowDelta, colDelta);
		row += rowDelta;
		col += colDelta;
		while (isInBounds(row, col) && this.board[row][col] == getOppositeColor()) {
			setSquare(row, col, getCurrentColor());
			row += rowDelta;
			col += colDelta;
		}
		return true;
	}	
	
	public boolean isValidDirection(int row, int col, int rowDelta, int colDelta) {
		if (!isInBounds(row, col) || !isInBounds(row + rowDelta, col + colDelta)) {
			return false;
		}
		if (this.board[row + rowDelta][col + colDelta] != getOppositeColor()) {
			return false;
		}
		row += rowDelta;
		col += colDelta;
		while (isInBounds(row, col) && this.board[row][col] == getOppositeColor()) {
			row += rowDelta;
			col += colDelta;
		}
		if (isInBounds(row, col) && this.board[row][col] == getCurrentColor()) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * A square can be played in if it is empty and placing a disk in it results in the capture of at least one of the opponent's pieces
	 */
	public boolean isValidMove(int row, int col) {
		if (this.board[row][col] != Square.EMPTY) {
			return false;
		}
		for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
			for (int colDelta = -1; colDelta <= 1; colDelta++) {
				if (!(rowDelta == 0 && colDelta == 0) && isValidDirection(row, col, rowDelta, colDelta)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isInBounds(int row, int col) {
		return (0 <= row && row < 8 && 0 <= col && col < 8);
	}

	public boolean makeMove(int row, int col) {
		boolean validMove = false;
		for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
			for (int colDelta = -1; colDelta <= 1; colDelta++) {
				if (!(rowDelta == 0 && colDelta == 0)) {
					if (flipPiecesInDirection(row, col, rowDelta, colDelta)) {
                        validMove = true;
                    }
				}
			}
		}
		if (validMove) {
			setSquare(row, col, getCurrentColor());
		}
		return validMove;
	}
	
	/*
	 *  This function returns an ArrayList of Pair objects corresponding to all of the valid moves.
	 */
	public ArrayList<SquareIndex> movesList() {
		ArrayList<SquareIndex> moves = new ArrayList<>();	
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isValidMove(i, j)) {
					moves.add(new SquareIndex(i, j));
				}
			}
		}
		return moves;
	}
	
	/*
	 *  This function determines if the game is in progress, is a tie, or if one of the players won. 
	 */
	public GameResult checkWin() {
		int numBlack = 0;
		int numWhite = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.board[i][j] == Square.BLACK) {
					numBlack++;
				}
				else if (this.board[i][j] == Square.WHITE) {
					numWhite++;
				}
			}
		}
		if(movesList().isEmpty()) {
			switchPlayer();
			if(movesList().isEmpty()) {
				if(numBlack == numWhite) {
					return GameResult.TIE;
				}
				else if(numBlack > numWhite) {
					return GameResult.BLACK_WIN;
				}
				else {
					return GameResult.WHITE_WIN;
				}
			}
			else {
				switchPlayer();
				return GameResult.IN_PROGRESS;
			}
		}
		else {
			return GameResult.IN_PROGRESS;
		}
	}

	// Prints board state
	public void printBoard() {
		for(int row = 0; row < 8; row++) {
			for(int col =0; col < 8; col++) {
				if(this.board[row][col] == Square.WHITE) {
						System.out.print("W");
				}
				else if	(this.board[row][col] == Square.BLACK) {
						System.out.print("B");
				}
				else {
					System.out.print("*");
				}
			System.out.println();
			}
		}
	}
	
	public SquareIndex randomValidMove() {
		ArrayList<SquareIndex> moves = movesList(); 
        Random rand = new Random();
        SquareIndex chosenMove = moves.get(rand.nextInt(moves.size()));
		return chosenMove;
	}
	
	/** 
	 *  This function calls movesList to obtain a list of all possible moves.
	 *  Then, we randomly pick moves from the array. We do this for both the computer and 
	 *  the player until the game is over. The winning player is returned. 
	 */
	/*public takeTurn(Player player)  {
		
	}*/
	
	
	/*public Player playRandomGame()  
	*/
}