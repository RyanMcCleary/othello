#include <stdio.h>
#include <errno.h>
#include "game_state.h"

int othello_assert(bool condition, const char *msg) {
    if (!condition) {
        fprintf(stderr, "%s test FAILED. :(\n", msg);
        return 0;
    } else {
        printf("%s test PASSED! :)\n", msg);
        return 1;
    }
}

int test_alloc(void) {
    struct game_state *state = game_state_alloc(1);
    int passed = othello_assert(state != NULL, "game_state_alloc(1)");
    if (state != NULL) game_state_free(state);
    return passed;
}

int test_init(void) {
    struct game_state state;
    game_state_init(&state);
    int passed = othello_assert(state.board[3][3] == SQUARE_BLACK &&
        state.board[4][4] == SQUARE_BLACK && state.board[3][4] == SQUARE_WHITE &&
        state.board[4][3] == SQUARE_WHITE, "game_state_init center center squares");
    passed += othello_assert(state.current_player == PLAYER_BLACK, "game_state_init current player");
    return passed;
}

int main(int argc, char **argv) {
    test_alloc();
    test_init();
    
    return 0;
}

