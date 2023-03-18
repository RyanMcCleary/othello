package othello;


public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		
		game.makeMove(4,2);
		game.switchPlayer();
		game.makeMove(5,4);
		game.switchPlayer();
		System.out.println(game.currentColor());
		System.out.println(game.isValidDirection(4, 5, 0, -1));
		//System.out.println(game.makeMove(4, 5));
		game.printBoard();

	}

}
