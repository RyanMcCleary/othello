package othello;

public class MCTSAgent {
    
    private GameTreeNode root;
    
    public MCTSAgent(GameState gameState) {
        this.root = new GameTreeNode(gameState);
    }
    
    /**
     * Starting at the given node, traverse the tree until we find 
     * a leaf node, returning that node.
     */
    public GameTreeNode traverse(GameTreeNode node) {
        throw new UnsupportedOperationException("... and the Lord said unto him, 'Thou shalt not call this method until it is implemented.'");
    }
    
    /**
     * Play the game all the way to the end by selecting random moves.
     * If this results in a win for white, we return 1.0. If it results in a win 
     * for black, return -1.0. If it results in a tie, return 0.0.
     * (We will likely return quantities other than just 1, 0, and -1 in the future.
     * For example, we might return some function of the ratio of white to black pieces.
     * This is a good starting point, though.)
     */
    public double rollout(GameTreeNode leaf) {
        throw new UnsupportedOperationException("... and the Lord said unto him, 'Thou shalt not call this method until it is implemented.'");
    }
    
    /**
     * After a rollout at the given leaf, we traverse back up the tree and update 
     * this leaf and all of its parents with the new information gained from this rollout.
     */
    public void backPropegate(GameTreeNode leaf, double reward) {
        throw new UnsupportedOperationException("... and the Lord said unto him, 'Thou shalt not call this method until it is implemented.'");
    }
    
    /**
     * Add a new node to the game tree as a child of the given leaf. 
     * For now, we will choose this uniformly at random. In the future, 
     * we will want to use a hueristic that takes the board position into account.
     */
    public void expand(GameTreeNode leaf) {
        throw new UnsupportedOperationException("... and the Lord said unto him, 'Thou shalt not call this method until it is implemented.'");
    }
    
    /**
     * Using the UCB protocol, select a child of the given (non-leaf) node.
     */
    public GameTreeNode selectChildUCB(GameTreeNode node) {
        throw new UnsupportedOperationException("... and the Lord said unto him, 'Thou shalt not call this method until it is implemented.'");
    }
    
}