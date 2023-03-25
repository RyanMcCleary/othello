# Othello

## BUILDING

This repository contains a gradle wrapper.
Assuming that you have installed the JDK, you can build the project using the command

	./gradlew build

on Mac and Linux, or

	gradlew build

on Windows. 
Run this command from the project's root directory (othello). 
After building, you can use the command './gradlew run' (or 'gradlew run' on Windows) 
to run the project.

## Game Tree

We will store the current board position in the root of the game tree, along with a tree
structure in which each node represents a possible move. These nodes will not need to store 
the state of the game at that point, since it can be reconstructed from the state stored in
the root and the sequence of moves stored in the tree up to a given node. 

To represent the game tree, we will need the following two classes:
  1. GameTreeNode: this class represents a move made in the game leading to a new game state.
     This class should contain a field specifying a move (of type SquareIndex), along with 
     a field of type ArrayList<GameTreeNode> for the children of this node. If a node has no
     children, this ArrayList will be empty. 
     Additionally, the GameTreeNode class should have a field called empiricalReward of type double.
     This will be used to keep track of an empirical estimate of how good the position specified by this node is.
     Finally, the GameTreeNode will also need a field numVisits of type int, in order to keep track of the number
     of times any given node has been visited in the MCTS algorithm. 
  2. GameTree: this class should contain a field of type Game, along with an ArrayList<GameTreeNode> of moves from this position.
     This class is essentially meant to wrap the game tree. The reason we need it is to prevent every node in the game tree from holding an 8x8 board.

We will also need appropriate accessor and convenience methods, of course. For example, we might want a method update(double reward) which updates the
empiricalReward and numVisits fields appropriately whenever this node is visited. 

## Useful Resources

To get started with the Monte Carlo Tree Search (MCTS) algorithm, have a look at
[this GeeksForGeeks page](https://www.geeksforgeeks.org/ml-monte-carlo-tree-search-mcts/) and 
[the Wikipedia article](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search).
Also, [this article](https://www.chessprogramming.org/Monte-Carlo_Tree_Search) on the Chess Programming Wiki is helpful.
These articles all mention the UCB (upper confidence bound) algorithm, which we will use to decide which positions to explore.
If you want to see the more technical details justifying UCB, [these lecture notes](https://ieor8100.github.io/mab/Lecture%203.pdf)
have a lot of information.
The notes do not mention MCTS in particular, but we can think of each board position as a multi-armed bandit, where each possible
move from that position is an arm, and the payoff is 1 if that move leads to a win and -1 otherwise.
If these lecture notes are unclear at first, you can also read [a previous set of notes](https://ieor8100.github.io/mab/Lecture%202.pdf)
from the same course introducing multi-armed bandits, and [the Wikipedia article](https://en.wikipedia.org/wiki/Multi-armed_bandit).

We will also need to think about how exactly the game tree will be represented.
One possible representation is using a [bitboard](https://www.chessprogramming.org/Bitboards).
We may not do this immediately in our first version, but it will be something to look into
when we start to optimize.
[This article](https://en.wikipedia.org/wiki/Board_representation_(computer_chess)) about board representations in chess could also be useful.
There is not as much information online about computer othello as there is on computer chess, but there is
[an article on the Chess Programming Wiki](https://www.chessprogramming.org/Othello) which might be a good read.

## TODOs

  1. Implement the traverse() method in MCTSAgent.
  2. Implement the rollout() method in MCTSAgent.
  3. Implement the backPropegate() method in MCTSAgent.
  4. Implement the expand() method in MCTSAgent.
  5. Implement the selectChildUCB() method in MCTSAgent.
  6. Set up the UI so that the board is always square (currently, the squares become rectangles when the board is resized, and the pieces become ovals. This results in a rather ugly look when the window is maximized.
  7. Move the makeRandomMove() function from Game to MCTSAgent.
  8. Make subpackages: we need one package for the representation/move evaluation (containing Game, MCTSAgent, etc), and another package for the UI. These packages could be called "othello.core" and "othello.ui", or something similar. 
  9. Load a board state in from a file.
