struct game_tree_node {
    game_state *state;
    struct game_tree_node *children;
    struct game_tree_node *parent;
    double empirical_reward;
    int num_visits;
};
