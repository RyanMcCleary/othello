#include "pool.h"
#include "MCTS_agent.h"
#include "game_state.h"

int main(int argc, char **argv)
{
    struct pool node_pool;
    pool_init(&node_pool, 1024 * 1024);
    game_tree_node *nodes = pool_alloc(&node_pool, 10);
    for (size_t i = 0; i < 10; i++) {
        game_state_init(&nodes[i]->game_state);
        game_state_print(&nodes[i]->game_state);
    }
    pool_destroy(&node_pool);
    return 0;
}
