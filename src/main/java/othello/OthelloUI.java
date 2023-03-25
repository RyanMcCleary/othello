package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OthelloUI extends JFrame {
        
    private GameState gameState;
        
    public OthelloUI() {
        super("Othello");
        this.gameState = new GameState();
        initializeComponents();
    }
    
    private void initializeComponents() {
        getContentPane().setLayout(new GridLayout(0, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OthelloSquareButton square = new OthelloSquareButton(i, j, this.gameState);
                square.setBackground(new Color(0x40, 0x53, 0x36));
                square.setMargin(new Insets(0, 0, 0, 0));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final int i_ = i, j_ = j;
                square.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameState.isValidMove(i_, j_)) {
                            gameState.makeMove(i_, j_);
                            gameState.switchPlayer();
                            getContentPane().repaint();
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