#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "game_state.h"

struct game_state *game_state_alloc(size_t num_elements) {
    return malloc(num_elements * sizeof(struct game_state));
}

struct game_state *game_state_init(struct game_state *state) {
    memset(state, 0, sizeof(struct game_state));
    state->board[3][3] = state->board[4][4] = SQUARE_BLACK;
    state->board[3][4] = state->board[4][3] = SQUARE_WHITE;
    return state;
}

void game_state_free(struct game_state *state) {
    free(state);
}

void game_state_print(struct game_state *state) {
    if (state->current_player == PLAYER_BLACK) {
        puts("current_player: PLAYER_BLACK");
    }
    else if (state->current_player == PLAYER_WHITE) {
        puts("current_player: PLAYER_WHITE");
    }
    else {
        puts("FIXME: unexpected value of current_player!");
    }
    for (size_t row = 0; row < 8; ++row) {
        for (size_t col = 0; col < 8; ++col) {
            switch(state->board[row][col]) {
            case SQUARE_BLACK:
                putchar('B');
                break;
            case SQUARE_WHITE:
                putchar('W');
                break;
            default:
                putchar('*');
            }
        }
        putchar('\n');
    }
}
