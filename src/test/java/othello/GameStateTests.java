package othello;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class GameStateTests {
    
    private GameState loadFromFile(String fileName) {
        InputStream configFile = getClass().getClassLoader().getResourceAsStream(fileName);
        return new GameState(configFile);
    }
    
    @Test
    @DisplayName("Loading default board state from a file matches the default constructor.")
    void loadGame() {
        GameState defaultState = new GameState();
        GameState loadedState = loadFromFile("default_state.txt");
        assertEquals(loadedState, defaultState);
    }
    
    @Test
    @DisplayName("GameState.switchPlayer behaves as expected.")
    void switchPlayerTest() {
        GameState gameState = new GameState();
        gameState.switchPlayer();
        assertEquals(gameState.getCurrentPlayer(), Player.WHITE);
    }
    
    @Test
    @DisplayName("Tests for GameState.isValidMove from the beginning of the game.")
    void isValidMoveTestBeginningOfGame() {
        GameState gameState = new GameState();
        assertTrue(gameState.isValidMove(3, 2), "isValidMove(3, 2) should be true");
        assertTrue(gameState.isValidMove(2, 3), "isValidMove(2, 3) should be true");
        assertTrue(gameState.isValidMove(5, 4), "isValidMove(5, 4) should be true");
        assertTrue(gameState.isValidMove(4, 5), "isValidMove(4, 5) should be true");
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (!(row == 2 && col == 3) && !(row == 3 && col == 2) && 
                    !(row == 4 && col == 5) && !(row == 5 && col == 4)) {
                    assertFalse(gameState.isValidMove(row, col), 
                        String.format("isValidMove(%d, %d) should be false.", row, col));
                }
            }
        }
    }
    
    @Test
    @DisplayName("Black placing a piece on (3, 2) behaves as expected.")
    void blackMove1() {
        GameState gameState = new GameState();
        gameState.makeMove(3, 2);
        GameState loadedFromFile = loadFromFile("black_turn1_3_2.txt");
        System.out.println(gameState.getCurrentPlayer());
        gameState.printBoard();
        System.out.println(loadedFromFile.getCurrentPlayer());
        loadedFromFile.printBoard();
        assertEquals(gameState, loadedFromFile);
    }
    
}
