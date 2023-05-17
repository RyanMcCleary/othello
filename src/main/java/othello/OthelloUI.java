package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;;

public class OthelloUI extends JFrame {
    
    public GameState gameState;
    public JPanel panel;
    public JCheckBox showMovesBox;
    public JLabel turnTracker;
    public ArrayList<ArrayList<JButton>> board;
    
    public OthelloUI() {
        super("Othello");
        this.gameState = new GameState();
        this.panel = initializePanel();
        this.board = initializeBoard();
        this.showMovesBox = initializeShowMovesBox();
        this.turnTracker = initializeTurnTracker();
    }

    public JPanel initializePanel() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(800,870));
        return panel;
    }

    public JLabel initializeTurnTracker() {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel tracker = new JLabel("Black to move");
        tracker.setOpaque(rootPaneCheckingEnabled);
        tracker.setForeground(Color.BLACK);
        tracker.setBackground(Color.WHITE);
        tracker.setHorizontalAlignment(SwingConstants.CENTER);
        tracker.setVerticalAlignment(SwingConstants.CENTER);
        tracker.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.panel.add(tracker, constraints);

        return tracker;
    }

    public JCheckBox initializeShowMovesBox() {
        GridBagConstraints constraints = new GridBagConstraints();
        JCheckBox showMoves = new JCheckBox("Show moves");
        showMoves.setForeground(Color.BLACK);
        showMoves.setBackground(Color.WHITE);
        showMoves.setHorizontalAlignment(SwingConstants.CENTER);
        showMoves.setVerticalAlignment(SwingConstants.CENTER);
        showMoves.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 4;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        constraints.weighty = 1;
        panel.add(showMoves, constraints);

        return showMoves;
    }

    public ArrayList<ArrayList<JButton>> initializeBoard() {
        ArrayList<ArrayList<JButton>> board = new ArrayList<ArrayList<JButton>>();
        for (int row = 0; row < 8; row++) {
            board.add(new ArrayList<JButton>());
            for (int col = 0; col < 8; col++) {
                board.get(row).add(initializeSquare(row, col));
            }
        }

        return board;
    }

    public JButton initializeSquare(int row, int col) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = row;
        constraints.gridy = col;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        OthelloSquareButton square = new OthelloSquareButton(row, col, this);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        square.setBackground(new Color(0x40, 0x53, 0x36));
        final int i_ = row, j_ = col;
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameState.isValidMove(i_, j_)) {
                    gameState.makeMove(i_, j_);
                    updateTurnTracker();
                    panel.repaint();
                    // MCTSAgent mctsAgent = new MCTSAgent(gameState);
                    // gameState = mctsAgent.makeBestMove();
                    // updateTurnTracker();
                    // panel.repaint();
                }
            }
        });
        panel.add(square, constraints);

        return square;
    }

    public void updateTurnTracker() {
        GameResult status = gameState.checkWin();
        if (status == GameResult.IN_PROGRESS) {
            if (gameState.getCurrentPlayer() == Player.BLACK) {
                turnTracker.setText("Black to move");
            }
            else {
                turnTracker.setText("White to move");
            }
        }
        else if (status == GameResult.BLACK_WIN) {
            turnTracker.setText("Black wins!");
        }
        else if (status == GameResult.WHITE_WIN) {
            turnTracker.setText("White wins!");
        }
        else {
            turnTracker.setText("Tie!");
        }
    }

}