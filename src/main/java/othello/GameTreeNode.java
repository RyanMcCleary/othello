package othello;
import java.util.ArrayList;

public class GameTreeNode {

    /* we will update this constant in the future based on experimentation */
    public static final double ucbConstant = 1.0;
    
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
 
    /** Is this node at the root of the game tree? */
    public boolean isRoot() {
        return this.parent == null;
    }
    
    /**
     * Return a child of this node, according to the UCB policy.
     * Preconditions: a node must have children in order to call this method on it.
     */
    public GameTreeNode selectChildUCB() {
        if (children.size() == 0) {
            throw new IllegalStateException("selectChildUCB() called on node with no children.");
        }
        GameTreeNode result = this.children.get(0);
        double maxUCBVal = result.getEmpiricalReward() +
                ucbConstant * Math.sqrt(Math.log(this.getNumVisits()) / result.getNumVisits());
        for (int i = 1; i < this.children.size(); i++) {
            GameTreeNode node = this.children.get(i);
            double ucbVal = node.getEmpiricalReward() +
                    ucbConstant * Math.sqrt(Math.log(this.getNumVisits()) / node.getNumVisits());
            if (ucbVal > maxUCBVal) {
                maxUCBVal = ucbVal;
                result = node;
            }
        }
        return result;
    }
    
    public double getEmpiricalReward() {
        return this.empiricalReward;
    }
    
    public int getNumVisits() {
        return this.numVisits;
    }
 
    public ArrayList<GameTreeNode> getChildren() {
        return this.children;
    }
 
    /**
     * Returns a copy of this node's gameState field.
     */
    public GameState getGameState() {
        return new GameState(this.gameState);
    }
 
}