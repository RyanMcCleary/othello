#include <stddef.h>
#include <stdio.h>
#include <math.h>

#include "pool.h"
#include "MCTS_agent.h"
#include "game_state.h"
#include "math.h"
static void increment_visits(struct game_tree_node *node);
static struct game_tree_node *select_child_ucb(struct game_tree_node *node, enum player player);
const double ucb_constant = 1.0;

int main(){
    printf("Hello Othello!");
    return 0;
}
/**
 * Play the game all the way to the end by selecting random moves.
 * If this results in a win for white, we return 1.0. If it results in a win 
 * for black, return -1.0. If it results in a tie, return 0.0.
 * (We will likely return quantities other than just 1, 0, and -1 in the future.
 * For example, we might return some function of the ratio of white to black pieces.
 * This is a good starting point, though.)
 */
double rollout(struct game_tree_node *leaf) {
    struct game_state *state = leaf->state;
    increment_visits(leaf);
    while (game_state_check_win(state) == GAME_RESULT_IN_PROGRESS) {
        if (game_state_count_moves(&state->board, state->current_player) == 0) {
            game_state_switch_player(state);
        }
        struct square_index *output_array;
        struct square_index random_square = game_state_random_move(state, output_array);
        int row = random_square.row;
        int col = random_square.col;
        game_state_make_move(state, row, col);
    }
    switch (game_state_check_win(state)) {
        case GAME_RESULT_BLACK_WIN: return -1.0;
        case GAME_RESULT_WHITE_WIN: return 1.0;
        default: return 0.0;
    }
}
    


static struct game_tree_node *traverse(struct game_tree_node *node) {
    increment_visits(node);
    struct game_tree_node *current_node = node;
    while (node->num_children > 0) {
        current_node = select_child_ucb(current_node, current_node->state->current_player);
        increment_visits(current_node);
    }
    return current_node;
}


/*
 *change argument to struct gtn
 *also struct on line 16. 
 */
static struct game_tree_node *select_child_ucb(struct game_tree_node *node, enum player player) {
    if (node->num_children == 0) {
        fprintf(stderr, "selectChildUCB() called on node with no children.\n");
        exit(1);
    }
    struct game_tree_node *result = &node->children[0];
    double max_ucb_val = result->empirical_reward +
        ucb_constant * sqrt(log(node->num_visits) / result->num_visits);
    for (int i = 1; i < node->num_children; i++) {
        struct game_tree_node* child = &node->children[i];
        double ucb_val;
        if (player == PLAYER_WHITE) {
            ucb_val = child->empirical_reward +
                ucb_constant * sqrt(log(node->num_visits) / child->num_visits);
        }
        else {
            ucb_val = child->empirical_reward -
                ucb_constant * sqrt(log(node->num_visits) / child->num_visits);
        }
        if (player == PLAYER_WHITE && ucb_val > max_ucb_val) {
            max_ucb_val = ucb_val;
            result = child;
        }
        if (player == PLAYER_BLACK && ucb_val < max_ucb_val) {
            max_ucb_val = ucb_val;
            result = child;
        }
    }
    return result;
}


static void increment_visits(struct game_tree_node *node) {
    ++node->num_visits;
}
 
/**
 * Updates empiricalReward to the new reward after a rollout.
 * We assume as a precondition to calling this method that numVisits has 
 * already been incremented from the last visit.
 * 
 * @param reward The numerical reward obtained from rolling out this node.
 */
void update_reward(double reward, struct game_tree_node *node) {
    node->empirical_reward = ((node->num_visits - 1) * 
        node->empirical_reward + reward) / node->empirical_reward;
}
 
    
/** Is this node at the root of the game tree? */
bool is_root(struct game_tree_node *node) {
    return (node->parent == NULL);
}

/**
 * After a rollout at the given leaf, we traverse back up the tree and update 
 * this leaf and all of its parents with the new information gained from this rollout.
 */
void back_propegate(struct game_tree_node *leaf, double reward) {
    for (struct game_tree_node *node = leaf; !(is_root(node)); node = node->parent) {
        update_reward(reward, node);
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
void expand(struct pool *pool, struct game_tree_node *leaf) {
    struct game_state *state = leaf->state;
    struct square_index *moves;
    int num_moves = game_state_list_moves(&state->board, state->current_player, moves);
    struct game_tree_node *children = pool_alloc(pool, num_moves);
    leaf->children = children;
    for (int i = 0; i < num_moves; i++) {
        leaf->children[i].parent = leaf;
        leaf->children[i].state = copy_make_move(state, moves[i]);
    }
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    