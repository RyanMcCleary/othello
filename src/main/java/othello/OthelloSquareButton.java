package othello;

import javax.swing.*;
import java.awt.*;

class OthelloSquareButton extends JButton {
    
    private Square squareState;
    
    public OthelloSquareButton() {
        this.squareState = Square.EMPTY;
    }
    
    public void setState(Square squareState) {
        this.squareState = squareState;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.squareState == Square.EMPTY) {
            return;
        }
        else if (this.squareState == Square.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(1, 1, getWidth() - 1, getHeight() - 1);
    }
                    
    
}