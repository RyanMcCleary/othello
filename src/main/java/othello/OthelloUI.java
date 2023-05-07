package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class OthelloUI extends JFrame {
        
    public GameState gameState;
    private boolean buttonsActive = true;

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
        JMenuBar menuBar;
        JMenu gameMenu, helpMenu;
        JMenuItem newGame, saveGame, loadGame, resumeGame;
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game Options");
        gameMenu.setMnemonic(KeyEvent.VK_G);        
        gameMenu.getAccessibleContext().setAccessibleDescription(
                "Game Options");
        newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGame.getAccessibleContext().setAccessibleDescription(
                "New Game");
        gameMenu.add(newGame);
        saveGame = new JMenuItem("Save Game");
        saveGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveGame.getAccessibleContext().setAccessibleDescription(
                "Save Game");
        gameMenu.add(saveGame);
        resumeGame = new JMenuItem("Resume Game");
        resumeGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        resumeGame.getAccessibleContext().setAccessibleDescription(
                "Resume Game");
        gameMenu.add(resumeGame);
        loadGame = new JMenuItem("Load Game");
        loadGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        loadGame.getAccessibleContext().setAccessibleDescription(
                "Load Game");
        gameMenu.add(loadGame);
        menuBar.add(gameMenu);
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.getAccessibleContext().setAccessibleDescription(
                "Help");
        menuBar.add(helpMenu);
        this.add(menuBar);
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
				square.setFocusable(true);
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