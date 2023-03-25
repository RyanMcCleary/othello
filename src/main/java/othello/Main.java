package othello;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                OthelloUI ui = new OthelloUI();
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ui.setVisible(true);
            }
        });
        */
		Game othello = new Game("boardconfiguration.txt");
		othello.printBoard();
	}

}
