#ifndef GAME_STATE_H
#define GAME_STATE_H

#include <stddef.h>

enum square_state {
    SQUARE_EMPTY = 0, SQUARE_BLACK, SQUARE_WHITE
};

enum player {
    PLAYER_BLACK = 0, PLAYER_WHITE
};

struct game_state {
    enum square_state board[8][8];
    enum player current_player;
};

struct game_state *game_state_alloc(size_t num_elements);

struct game_state *game_state_init(struct game_state *state);

void game_state_free(struct game_state *state);

void game_state_print(struct game_state *state);

#endif
