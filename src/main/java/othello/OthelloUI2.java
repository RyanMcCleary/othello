package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;;

public class OthelloUI2 extends JFrame {
    
    public GameState gameState;
    
    public OthelloUI2() {
        super("Othello");
        this.gameState = new GameState();
        initializeComponents();
    }
    
    private void initializeComponents() {
        JPanel gbp = new JPanel();
        this.add(gbp);
        gbp.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JCheckBox show_moves = new JCheckBox("Show moves");
        show_moves.setForeground(Color.BLACK);
        show_moves.setBackground(Color.WHITE);
        show_moves.setHorizontalAlignment(SwingConstants.CENTER);
        show_moves.setVerticalAlignment(SwingConstants.CENTER);
        show_moves.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        show_moves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show_moves.isSelected()) {
                    show_moves.setText("Hide moves");
                    showMoves(gbp);
                }
                else {
                    show_moves.setText("Show moves");
                    hideMoves(gbp);
                }
            }
        });
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbp.add(show_moves, gbc);

        JLabel turn_msg = new JLabel("Black to move");
        // turn_msg.setFont(new Font("Consolas", Font.PLAIN, 30));
        turn_msg.setOpaque(rootPaneCheckingEnabled);
        turn_msg.setForeground(Color.BLACK);
        turn_msg.setBackground(Color.WHITE);
        turn_msg.setHorizontalAlignment(SwingConstants.CENTER);
        turn_msg.setVerticalAlignment(SwingConstants.CENTER);
        turn_msg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbp.add(turn_msg, gbc);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.weightx = 1;
                gbc.weighty = 1;
                OthelloSquareButton square = new OthelloSquareButton(i, j, this);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setBackground(new Color(0x40, 0x53, 0x36));
                final int i_ = i, j_ = j;
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameState.isValidMove(i_, j_)) {
                            gameState.makeMove(i_, j_);
                            if (gameState.getCurrentPlayer() == Player.BLACK) {
                                turn_msg.setText("Black to move");
                            }
                            else {
                                turn_msg.setText("White to move");
                            }
                            gbp.repaint();
                            // MCTSAgent mctsAgent = new MCTSAgent(gameState);
                            // gameState = mctsAgent.makeBestMove();
                            // if (gameState.getCurrentPlayer() == Player.BLACK) {
                            //     turn_msg.setText("Black to move");
                            // }
                            // else {
                            //     turn_msg.setText("White to move");
                            // }
                            // gbp.repaint();
                        }
                    }
                });
                gbp.add(square, gbc);
            }
        }

        setMinimumSize(new Dimension(800, 870));
    }
 
    public void showMoves(JPanel gbp) {
        ArrayList<SquareIndex> SIlist = gameState.movesList();
        int n = SIlist.size();
        ArrayList<int[]> list = new ArrayList<int[]>();
        for (int k = 0; k < n; k++) {
            list.add(k, new int[] {SIlist.get(k).getRow(), SIlist.get(k).getColumn()});
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Component square = gbp.getComponentAt(i, j);
                int[] index = new int[]{i,j};

                if (list.contains(index)) {
                    square.setBackground(Color.GREEN);
                }
                else {
                    square.setBackground(new Color(0x40, 0x53, 0x36));
                }
            }
        }
        gbp.repaint();
    }

    public void hideMoves(JPanel gbp) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Component square = gbp.getComponentAt(i, j);
                square.setBackground(new Color(0x40, 0x53, 0x36));
            }
        }
        gbp.repaint();
    }
}