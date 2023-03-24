package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OthelloUI extends JFrame {
        
    Square currentColor = Square.BLACK;
        
    public OthelloUI() {
        super("Othello");
        initializeComponents();
    }
    
    private void initializeComponents() {
        getContentPane().setLayout(new GridLayout(0, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OthelloSquareButton square = new OthelloSquareButton();
                square.setBackground(new Color(0x40, 0x53, 0x36));
                square.setMargin(new Insets(0, 0, 0, 0));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        square.setState(currentColor);
                        square.repaint();
                        if (currentColor == Square.BLACK) {
                            currentColor = Square.WHITE;
                        }
                        else {
                            currentColor = Square.BLACK;
                        }
                    }
                    
                });
                this.add(square);
            }
        }
        setMinimumSize(new Dimension(500, 500));
        this.pack();
    }
    
}