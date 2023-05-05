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
bool isValidDirection(struct game_state *state, int row, int col, int rowDelta, int colDelta) {
		if (!in_bounds(row, col) || !in_bounds(row + rowDelta, col + colDelta)) {
			return false;
		}
		if (state->board[row + rowDelta][col + colDelta] != opposite_color(state)) {
			return false;
		}
		row += rowDelta;
		col += colDelta;
		while (in_bounds(row, col) && state->board[row][col] == opposite_color(state)) {
			row += rowDelta;
			col += colDelta;
		}
		if (in_bounds(row, col) && state->board[row][col] == current_color()) {
			return true;
		}
		else {
			return false;
		}
}

bool in_bounds(int row, int col) {
		return (0 <= row && row < 8 && 0 <= col && col < 8);
	}


enum square_state current_color(struct game_state *state) {	
	if(state->player = player_black) {
		return square_black;
	}
	else {
		return square_white;
	}	
}

enum square_state opposite_color(struct game_state *state) {	
	if(state->player = player_black) {
		return square_white;
	}
	else {
		return square_black;
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

bool game_state_flip_direction(struct game_state *state, int row, int col, int rowDelta, int colDelta) 
{
		if (!isValidDirection(row, col, rowDelta, colDelta)) {
			return false;
		}
		row += rowDelta;
		col += colDelta;
		while (isInBounds(row, col) && this.board[row][col] == getOppositeColor()) {
			setSquare(row, col, getCurrentColor());
			row += rowDelta;
			col += colDelta;
		}
		return true;
}
