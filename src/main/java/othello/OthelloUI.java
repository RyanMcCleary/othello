package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OthelloUI extends JFrame {
        
    public GameState gameState;
    boolean buttonsActive = true;

    public class MCTSWorker extends SwingWorker<GameState, Object> {
        
        private MCTSAgent agent;
        
        public MCTSWorker(GameState currentGameState) {
            this.agent = new MCTSAgent(currentGameState);
        }
        
        @Override
        public GameState doInBackground() {
            return this.agent.makeBestMove();
        }
        
        @Override
        public void done() {
            try {
                gameState = get();
                getContentPane().repaint();
                buttonsActive = true;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
       
    public OthelloUI() {
        super("Othello");
        this.gameState = new GameState();
        initializeComponents();
    }
    
    private void initializeComponents() {
        getContentPane().setLayout(new GridLayout(0, 8));
        JLabel turn_msg = new JLabel("Black to move");
        turn_msg.setPreferredSize(new Dimension(50, 50));
        turn_msg.setFont(new Font("Consolas", Font.PLAIN, 20));
        turn_msg.setHorizontalAlignment(SwingConstants.CENTER);
        turn_msg.setVerticalAlignment(SwingConstants.CENTER);
        turn_msg.setBackground(new Color(0x40, 0x53, 0x36));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OthelloSquareButton square = new OthelloSquareButton(i, j, this);
                square.setBackground(new Color(0x40, 0x53, 0x36));
                square.setMargin(new Insets(0, 0, 0, 0));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final int i_ = i, j_ = j;
                square.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttonsActive && gameState.isValidMove(i_, j_)) {
                            gameState.makeMove(i_, j_);
                            if (gameState.getCurrentPlayer() == Player.BLACK) {
                                turn_msg.setText("Black to move");
                            }
                            else {
                                turn_msg.setText("White to move");
                            }
                            getContentPane().repaint();                            
                            (new MCTSWorker(gameState)).execute();
                            buttonsActive = false;
                        }
                    }
                    
                });
                this.add(square);
            }
        }
        this.add(turn_msg);
        setMinimumSize(new Dimension(800, 850));
        this.pack();
    }
    
}