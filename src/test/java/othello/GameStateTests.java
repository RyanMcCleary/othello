package othello;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @DisplayName("Black placing a piece on (3, 2) behaves as expected.")
    void blackMove1() {
        GameState gameState = new GameState();
        gameState.makeMove(3, 2);
        GameState loadedFromFile = loadFromFile("black_move_1.txt");
        System.out.println(gameState.getCurrentPlayer());
        gameState.printBoard();
        System.out.println(loadedFromFile.getCurrentPlayer());
        loadedFromFile.printBoard();
        assertEquals(gameState, loadedFromFile);
    }
    
}
