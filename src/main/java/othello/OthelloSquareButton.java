package othello;

import javax.swing.*;
import java.awt.*;

class OthelloSquareButton extends JButton {
    
    private GameState gameState;
    private int row;
    private int col;
    
    public OthelloSquareButton(int row, int col, GameState g) {
        this.gameState = g;
        this.row = row;
        this.col = col;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.gameState.getSquare(row, col) == Square.EMPTY) {
            return;
        }
        else if (this.gameState.getSquare(row, col) == Square.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(1, 1, getWidth() - 1, getHeight() - 1);
    }
                    
    
}