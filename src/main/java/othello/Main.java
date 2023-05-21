package othello;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
// import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws Exception {
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                OthelloUI ui = new OthelloUI();
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ui.setVisible(true);
            }
            
        });
        
	}

}
