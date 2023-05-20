#ifndef GAME_STATE_H
#define GAME_STATE_H

#include <stddef.h>
#include <stdbool.h>
#include <string.h>

enum game_result {
    GAME_RESULT_BLACK_WIN = 0,
    GAME_RESULT_WHITE_WIN,
    GAME_RESULT_IN_PROGRESS,
    GAME_RESULT_TIE
};


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

struct square_index {
    int row;
    int col;
};

enum game_result game_state_check_win(struct game_state *state); 

struct game_state *game_state_alloc(size_t num_elements);

size_t game_state_count_moves(enum square_state (*board)[8][8], enum player current_player);

struct game_state *game_state_init(struct game_state *state);

struct game_state *copy_make_move(struct game_state *state, struct square_index index);

void game_state_switch_player(struct game_state *state);

void game_state_free(struct game_state *state);

void game_state_print(struct game_state *state);

bool game_state_valid_move(struct game_state *state, int row, int col);

void game_state_make_move(struct game_state *state, int row, int col);

struct square_index game_state_random_move(struct game_state *state,
                                         struct square_index *output_array);

size_t game_state_list_moves(enum square_state (*board)[8][8], enum player current_player, 
                             struct square_index *output_array);

void square_index_init(struct square_index *index, int row, int col);

struct game_state *game_state_load_from_file(struct game_state *state, FILE *fp);

struct game_state *game_state_load_from_path(struct game_state *state, char *path);

#endif
