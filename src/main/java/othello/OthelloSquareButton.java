package othello;

import javax.swing.*;
import java.awt.*;

class OthelloSquareButton extends JButton {
    
    private OthelloUI parent;
    private int row;
    private int col;

    public OthelloSquareButton(int row, int col, OthelloUI parent) {
        this.parent = parent;
        this.row = row;
        this.col = col;
    }

    public void setSquareColor() {
        if (this.parent.showMovesBox.isSelected() && this.parent.gameState.movesList(this.parent.gameState.getCurrentPlayer()).contains(new SquareIndex(row,col))) {
            this.parent.board.get(row).get(col).setBackground(Color.GREEN);
        }
        else {
            this.parent.board.get(row).get(col).setBackground(new Color(0x40, 0x53, 0x36));
        }
        this.parent.panel.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setSquareColor();
        if (this.parent.gameState.getSquare(row, col) == Square.EMPTY) {
            return;
        }
        else if (this.parent.gameState.getSquare(row, col) == Square.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(1, 1, getWidth() - 1, getHeight() - 1);
    }
                    
}