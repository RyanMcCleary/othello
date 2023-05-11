#include <stddef.h>
#include <stdio.h>
#include "pool.h"
#include "MCTS_agent.h"


/**
 * Play the game all the way to the end by selecting random moves.
 * If this results in a win for white, we return 1.0. If it results in a win 
 * for black, return -1.0. If it results in a tie, return 0.0.
 * (We will likely return quantities other than just 1, 0, and -1 in the future.
 * For example, we might return some function of the ratio of white to black pieces.
 * This is a good starting point, though.)
 */
static double rollout(game_tree_node *leaf) {
    game_state state = leaf->game_state;
    increment_visits(leaf);
    while (checkWin(state) == GameResult.IN_PROGRESS) {
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
    


static game_tree_node* traverse(game_tree_node* node) {
    increment_visits(node);
    game_tree_node* current_node = node;
    while (node->num_children > 0) {
        current_node = select_child_ucb(current_node, (current_node->game_state)->current_player;
        increment_visits(current_node);
    }
    return current_node;
}



static game_tree_node* select_child_ucb(game_tree_node* node, Player player) {
    if (node->num_children == 0) {
        fprintf(stderr, "selectChildUCB() called on node with no children.\n");
        exit(1);
    }
    game_tree_node* result = node->children[0];
    double max_ucb_val = result->empirical_reward +
        node->ucb_constant * sqrt(log(get_num_visits(node)) / get_num_visits(result));
    for (int i = 1; i < node->num_children; i++) {
        game_tree_node* child = node->children[i];
        double ucb_val;
        if (player == WHITE) {
            ucb_val = child->empirical_reward +
                node->ucb_constant * sqrt(log(get_num_visits(node)) / get_num_visits(child));
        }
        else {
            ucb_val = child->empirical_reward -
                node->ucb_constant * sqrt(log(get_num_visits(node)) / get_num_visits(child));
        }
        if (player == WHITE && ucb_val > max_ucb_val) {
            max_ucb_val = ucb_val;
            result = child;
        }
        if (player == BLACK && ucb_val < max_ucb_val) {
            max_ucb_val = ucb_val;
            result = child;
        }
    }
    return result;
}


static game_tree_node** get_children(game_tree_node* node) {
    return node->children;
}
 

static game_state get_game_state(game_tree_node* node) {
    return node->game_state;
}


static void set_children(game_tree_node* node, game_tree_node** children, int num_children) {
    node->children = malloc(sizeof(game_tree_node*) * num_children);
    memcpy(node->children, children, sizeof(game_tree_node*) * num_children);
}


static game_tree_node get_parent(game_tree_node node) {
    return node.parent;
}

static void increment_visits(game_tree_node node) {
    ++node.numVisits;
}
 
/**
 * Updates empiricalReward to the new reward after a rollout.
 * We assume as a precondition to calling this method that numVisits has 
 * already been incremented from the last visit.
 * 
 * @param reward The numerical reward obtained from rolling out this node.
 */
static void update_reward(double reward, game_tree_node node) {
    node.empirical_reward = ((num_visits(node) - 1) * 
    node.empirical_reward + reward) / node.empirical_reward;
}
 

/**
 * Starting at the given node, traverse the tree until we find 
 * a leaf node, returning that node.
 */
static game_tree_node traverse(game_tree_node *node) {
    increment_visits(node);
    game_tree_node currentNode = *node;
    while (getChildren(currentNode).size() > 0) {
        currentNode = currentNode.selectChildUCB(currentNode.getGameState().getCurrentPlayer());
        currentNode.incrementVisits();
    }
    return currentNode;
}
    
/** Is this node at the root of the game tree? */
static bool is_root(game_tree_node *node) {
    return (node->parent == NULL);
}

static game_tree_node getRoot(game_tree_node *node) {
    return node->root;
}
    