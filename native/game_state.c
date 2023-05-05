#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "game_state.h"


static bool in_bounds(int row, int col);

static enum square_state opposite_color(struct game_state *state);

static enum square_state current_color(struct game_state *state);

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

static bool valid_direction(struct game_state *state, int row, int col,
                      int row_delta, int col_delta) {
    if (!in_bounds(row, col) ||
        !in_bounds(row + row_delta, col + col_delta)) {
        return false;
    }
    if (state->board[row + row_delta][col + col_delta] != opposite_color(state)) {
        return false;
    }
    row += row_delta;
    col += col_delta;
    while (in_bounds(row, col) &&
           state->board[row][col] == opposite_color(state)) {
        row += row_delta;
        col += col_delta;
    }
    if (in_bounds(row, col) &&
        state->board[row][col] == current_color(state)) {
        return true;
    }
    else {
        return false;
    }
}

static bool in_bounds(int row, int col) {
    return (0 <= row && row < 8 && 0 <= col && col < 8);
}

static enum square_state current_color(struct game_state *state) {	
    if(state->current_player == PLAYER_BLACK) {
        return SQUARE_BLACK;
    }
    else {
        return SQUARE_WHITE;
    }
}

static enum square_state opposite_color(struct game_state *state) {	
    if(state->current_player == PLAYER_BLACK) {
        return SQUARE_WHITE;
    }
    else {
        return SQUARE_BLACK;
    }
}

void game_state_print(struct game_state *state) {
    if (state->current_player == PLAYER_BLACK) {
        puts("current_player: PLAYER_BLACK");
    }
    else if (state->current_player == PLAYER_WHITE) {
        puts("current_player: PLAYER_WHITE");
    }
    else {
        fputs("FIXME: unexpected value of current_player!", stderr);
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
            case SQUARE_EMPTY:
                putchar('*');
                break;
            default:
                fprintf(stderr, "\nFIXME: Unexpected value found in board[%zu][%zu]\n", row, col);
            }
        }
        putchar('\n');
    }
}

static bool flip_direction(struct game_state *state, int row, int col, int row_delta, int col_delta) {
    if (!valid_direction(state, row, col, row_delta, col_delta)) {
        return false;
    }
    row += row_delta;
    col += col_delta;
    while (in_bounds(row, col) &&
           state->board[row][col] == opposite_color(state)) {
        state->board[row][col] = current_color(state);
        row += row_delta;
        col += col_delta;
    }
    return true;
}
