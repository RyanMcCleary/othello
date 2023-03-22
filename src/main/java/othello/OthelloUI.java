package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OthelloUI extends JFrame {
        
    public OthelloUI() {
        super("Othello");
        initializeComponents();
    }
    
    private void initializeComponents() {
        getContentPane().setLayout(new GridLayout(0, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int i_ = i, j_ = j;
                JButton square = new JButton("*");
                square.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(getContentPane(), "Square (" + i_ + ", " + j_ + ") pressed!");
                    }
                    
                });
                this.add(square);
            }
        }
        setMinimumSize(new Dimension(500, 500));
        this.pack();
    }
    
}