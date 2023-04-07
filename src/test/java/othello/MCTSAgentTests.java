package othello;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class MCTSAgentTests {
    
    private GameState loadFromFile(String fileName) {
        InputStream configFile = getClass().getClassLoader().getResourceAsStream(fileName);
        return new GameState(configFile);
    }
    
    @Test
    @DisplayName("Test MCTSAgent.expand from beginning of game.")
    void testExpandBeginningOfGame() {
        MCTSAgent agent = new MCTSAgent(new GameState());
        agent.expand(agent.getRoot());
        assertEquals(agent.getRoot().getChildren().size(), 4, "There should be 4 possible moves from the beginning of the game.");
        ArrayList<GameState> expectedChildStates = new ArrayList<>();
        expectedChildStates.add(loadFromFile("black_turn1_2_3.txt"));
        expectedChildStates.add(loadFromFile("black_turn1_3_2.txt"));
        expectedChildStates.add(loadFromFile("black_turn1_4_5.txt"));
        expectedChildStates.add(loadFromFile("black_turn1_5_4.txt"));
        boolean childStatesAreAsExpected = agent.getRoot().getChildren().stream()
            .anyMatch(node -> expectedChildStates.stream()
                .anyMatch(state -> state.equals(node.getGameState())));
        assertTrue(childStatesAreAsExpected, "MCTSAgent.expand() resulted in invalid moves.");
    }
 
    @Test
    @DisplayName("Rollout tests.")
    void rolloutTests() {
        MCTSAgent agent = new MCTSAgent(new GameState());
        GameTreeNode root = agent.getRoot();
        agent.expand(root);
        ArrayList<GameTreeNode> children = root.getChildren();
        agent.rollout(children.get(0));
        assertEquals(children.get(0).getNumVisits(), 1, 
            "The number of visits for a node should be 1 after the first time it was rolled out.");
        assertEquals(children.get(0).getChildren().size(), 0,
            "The rollout operation should not add any more nodes to the game tree.");
    }
 
}