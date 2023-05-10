typedef struct game_tree_node {
    double ucb_constant;
    game_state game_state;
    struct game_tree_node** children;
    struct game_tree_node* parent;
    double empirical_reward;
    int num_visits;
} game_tree_node;
