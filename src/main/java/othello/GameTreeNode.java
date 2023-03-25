package othello;
import java.util.ArrayList;

public class GameTreeNode {
    
    private GameState gameState;
    private ArrayList<GameTreeNode> children;
    private double empiricalReward;
    private int numVisits;
    
    public GameTreeNode(GameState gameState) {
        this.gameState = gameState;
        this.children = new ArrayList<GameTreeNode>();
        this.empiricalReward = 0.0;
        this.numVisits = 0;
    }
    
}