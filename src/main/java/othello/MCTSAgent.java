package othello;

import java.util.ArrayList;

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
        node.incrementVisits();
        GameTreeNode currentNode = node;
        while (currentNode.getChildren().size() > 0) {
            currentNode = currentNode.selectChildUCB(currentNode.getGameState().getCurrentPlayer());
            currentNode.incrementVisits();
        }
        return currentNode;
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
        GameState gameState = leaf.getGameState();
        leaf.incrementVisits();
        while (gameState.checkWin() == GameResult.IN_PROGRESS) {
            if (gameState.movesList().size() == 0) {
                gameState.switchPlayer();
            }
            gameState.makeMove(gameState.randomValidMove());
        }
        switch (gameState.checkWin()) {
            case BLACK_WIN: return -1.0;
            case WHITE_WIN: return 1.0;
            default: return 0.0;
        }
    }
    
    /**
     * After a rollout at the given leaf, we traverse back up the tree and update 
     * this leaf and all of its parents with the new information gained from this rollout.
     */
    public void backPropegate(GameTreeNode leaf, double reward) {
        for (GameTreeNode node = leaf; !node.isRoot(); node = node.getParent()) {
            node.updateReward(reward);
        }
    }
    
    /**
     * For each move which can be made from the current game state represented
     * in the given node, create a new child node where that move was made.
     *
     * @param leaf The node to expand.
     *
     * This method assumes that leaf is, in fact, a leaf node (i.e. leaf has no children).
     */
    public void expand(GameTreeNode leaf) {
        GameState gameState = leaf.getGameState();
        ArrayList<GameTreeNode> children = new ArrayList<>();
        ArrayList<SquareIndex> moves = gameState.movesList();
        for (SquareIndex move : moves) {
            children.add(new GameTreeNode(gameState.copyAndMakeMove(move), leaf));
        }
        leaf.setChildren(children);
    }
    
    public GameState makeBestMove() {
        for (int i = 0; i < 500000; i++) {
            GameTreeNode leaf = traverse(this.root);
            expand(leaf);
            for (GameTreeNode child : leaf.getChildren()) {
                double reward = rollout(child);
                backPropegate(child, reward);
            }
        }
        ArrayList<GameTreeNode> rootChildren = root.getChildren();
        if (rootChildren.size() == 0) {
            return null;
        }
        GameTreeNode bestChild = rootChildren.get(0);
        for (int i = 1; i < rootChildren.size(); i++) {
            GameTreeNode currentChild = rootChildren.get(i);
            if (currentChild.getEmpiricalReward() > bestChild.getEmpiricalReward()) {
                bestChild = currentChild;
            }
        }
        return bestChild.getGameState();
    }
    
    public GameTreeNode getRoot() {
        return this.root;
    }
    
}