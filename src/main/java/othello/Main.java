package othello;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
        
        OthelloUI ui = new OthelloUI();
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
        
		Game game = new Game();
		
		game.makeMove(4,2);
		game.switchPlayer();
		game.makeMove(5,4);
		game.switchPlayer();
		System.out.println(game.getCurrentColor());
		System.out.println(game.isValidDirection(4, 5, 0, -1));
		//System.out.println(game.makeMove(4, 5));
		game.printBoard();

	}

}
