package othello;

import javax.swing.*;
import java.awt.*;

class OthelloSquareButton2 extends JButton {
    
    private OthelloUI2 parent;
    private int row;
    private int col;

    public OthelloSquareButton2(int row, int col, OthelloUI2 parent) {
        this.parent = parent;
        this.row = row;
        this.col = col;
    }

    public void setSquareColor() {
        if (parent.showMovesBox.isSelected() && BoardOperations.isLegal(parent.gameState.bboardB, parent.gameState.bboardW, parent.currentPlayer, row+1, col+1)) {
            parent.board.get(row).get(col).setBackground(Color.GREEN);
        }
        else {
            parent.board.get(row).get(col).setBackground(new Color(0x40, 0x53, 0x36));
        }
        parent.panel.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setSquareColor();
        if (BoardOperations.getSquare(parent.gameState.bboardB, parent.gameState.bboardW, row+1, col+1) == Square.EMPTY) {
            return;
        }
        else if (BoardOperations.getSquare(parent.gameState.bboardB, parent.gameState.bboardW, row+1, col+1) == Square.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(1, 1, getWidth() - 1, getHeight() - 1);
    }
                    
}