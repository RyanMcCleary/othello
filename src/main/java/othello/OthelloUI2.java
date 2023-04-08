package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OthelloUI2 extends JFrame {
        
    public GameState gameState;
        
    public OthelloUI2() {
        super("Othello");
        this.gameState = new GameState();
        initializeComponents();
    }
    
    private void initializeComponents() {
        JPanel gridPanel = new JPanel();
        JPanel gridBagPanel = new JPanel();
        JButton button1 = new JButton();
        gridBagPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridBagPanel.add(gridPanel, gbc);
        setContentPane(gridPanel);
        getContentPane().setLayout(new GridLayout(8,8,0,0));
        // GridBagConstraints c = new GridBagConstraints();

        // JLabel turn_msg = new JLabel("Black to move");
        // turn_msg.setPreferredSize(new Dimension(50, 50));
        // turn_msg.setFont(new Font("Consolas", Font.PLAIN, 20));
        // turn_msg.setHorizontalAlignment(SwingConstants.CENTER);
        // turn_msg.setVerticalAlignment(SwingConstants.CENTER);
        // turn_msg.setBackground(new Color(0x40, 0x53, 0x36));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OthelloSquareButton square = new OthelloSquareButton(i, j, this);
                square.setBackground(new Color(0x40, 0x53, 0x36));
                // square.setMargin(new Insets(0, 0, 0, 0));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setPreferredSize(new Dimension(10,10));
                final int i_ = i, j_ = j;
                square.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameState.isValidMove(i_, j_)) {
                            gameState.makeMove(i_, j_);

                            // if (gameState.getCurrentPlayer() == Player.BLACK) {
                            //     turn_msg.setText("Black to move");
                            // }
                            // else {
                            //     turn_msg.setText("White to move");
                            // }
                            setContentPane(gridBagPanel);
                            getContentPane().repaint();       
                            setContentPane(gridPanel);                     
                            MCTSAgent mctsAgent = new MCTSAgent(gameState);
                            gameState = mctsAgent.makeBestMove();
                            setContentPane(gridBagPanel);
                            getContentPane().repaint();
                            setContentPane(gridPanel);
                        }
                    }
                    
                });

                this.add(square);
            }
        }

        // this.add(turn_msg);
        gbc.gridy = 1;
        // setContentPane(gridBagPanel);
        this.add(button1, gbc);

        setMinimumSize(new Dimension(800, 800));
        // this.pack();
    }
    
}