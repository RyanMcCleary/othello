package othello;
import java.util.ArrayList;

public class GameTreeNode {
    
    private GameState gameState;
    private ArrayList<GameTreeNode> children;
    private GameTreeNode parent;
    private double empiricalReward;
    private int numVisits;
    
    public GameTreeNode(GameState gameState, GameTreeNode parent) {
        this.gameState = gameState;
        this.children = new ArrayList<GameTreeNode>();
        this.parent = parent;
        this.empiricalReward = 0.0;
        this.numVisits = 0;
    }
    
    public GameTreeNode(GameState gameState) {
        this(gameState, null);
    }
    
    public boolean isRoot() {
        return this.parent == null;
    }
    
}