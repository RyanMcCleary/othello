#ifndef POOL_H_INCLUDED
#define POOL_H_INCLUDED

#include <stdlib.h>
#include "game_state.h"


struct pool {
    struct game_tree_node *array;
    size_t capacity;
    size_t num_allocated;
};

struct pool *pool_init(struct pool *pool, size_t capacity);

struct game_tree_node *pool_alloc(struct pool *pool, size_t len);

void pool_destroy(struct pool *pool);

#endif