package othello;
import java.util.ArrayList;

public class GameTreeNode {
    private SquareIndex move;
    private ArrayList<GameTreeNode> children;
    private double empiricalReward;
    private int numVisits;
}