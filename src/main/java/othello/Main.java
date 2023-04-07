package othello;

import javax.swing.JFrame;
import java.io.FileNotFoundException;
public class Main {

	public static void main(String[] args) {
        
      /* javax.swing.SwingUtilities.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                OthelloUI ui = new OthelloUI();
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ui.setVisible(true);
            }
        });*/
        try{
		  GameState othello = new GameState("C:\\Users\\charl\\othello\\src\\main\\java\\othello\\boardconfiguration.txt");
		  othello.printBoard();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Configuation file not found.\n");
		}
		
	}

}
