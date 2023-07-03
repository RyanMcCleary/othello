package othello;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
// import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws Exception {
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                OthelloUI2 ui = new OthelloUI2();
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ui.setVisible(true);
            }
            
        });
        
	}

}
