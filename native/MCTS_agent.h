#ifndef MCTS_AGENT_H_INCLUDED
#define MCTS_AGENT_H_INCLUDED

#include "game_state.h"

struct game_tree_node {
    struct game_state *state;
    struct game_tree_node *children;
    struct game_tree_node *parent;
    double empirical_reward;
    int num_visits;
    int num_children;
};

#endif