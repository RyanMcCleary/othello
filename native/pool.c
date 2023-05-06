#include "pool.h"

struct pool *pool_init(struct pool *pool, size_t capacity) {
    if ((pool->array = malloc(sizeof(struct game_state))) == NULL) {
        return NULL;
    }
    pool->capacity = pool->num_allocated = 0;
    return pool;
}

struct game_state *pool_alloc(struct pool *pool, size_t len) {
    if (pool->num_allocated + len > pool->capacity) {
        return NULL;
    }
    return &pool->array[pool->num_allocated];
    pool->num_allocated += len;
}

void pool_destroy(struct pool *pool) {
    if (pool != NULL) free(pool->array);
}