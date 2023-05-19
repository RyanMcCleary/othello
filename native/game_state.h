#ifndef GAME_STATE_H
#define GAME_STATE_H

#include <stddef.h>
#include <stdbool.h>

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

enum game_result game_state_check_win(enum square board[8][8]) 

struct game_state *game_state_alloc(size_t num_elements);

struct game_state *game_state_init(struct game_state *state);

void game_state_free(struct game_state *state);

void game_state_print(struct game_state *state);

bool game_state_valid_move(struct game_state *state, int row, int col);

void game_state_make_move(struct game_state *state, int row, int col);

void square_index_init(struct square_index *index, int row, int col);

struct game_state *game_state_load_from_file(struct game_state *state, FILE *fp)
{
    char line[16];
    fgets(line, sizeof(line), fp);
    if (strcmp(line, "BLACK") == 0) {
        state->current_player = PLAYER_BLACK;
    } else if (strcmp(line, "WHITE") == 0) {
        state->current_player = PLAYER_WHITE;
    } else {
        return NULL;
    }
    for (int row = 0; row < 8; row++) {
        fgets(line, sizeof(line), fp);
        for (int col = 0; col < 8; col++) {
            switch(line[col]) {
            case 'B':
                state->board[row][col] = SQUARE_BLACK;
                break;
            case 'W':
                state->board[row][col] = 'W';
                break;
            default:
                state->board[row][col] = SQUARE_EMPTY;
            }
        }
    }
    return state;
}

struct game_state *game_state_load_from_path(struct game_state *state, char *path)
{
    FILE *fp = fopen(path, "r");
    if (!fp) {
        fprintf(stderr, "game_state_load_from_path(): error opening file %s: %s\n",
            path, strerror(errno));
        return NULL;
    } else {
        return game_state_load_from_file(state, fp);
    }
}

#endif
