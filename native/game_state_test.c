#include <stdio.h>
#include <errno.h>
#include "game_state.h"

int main(int argc, char **argv) {
    struct game_state state, *state_p;
    puts("Testing statically allocated game_state...");
    game_state_init(&state);
    game_state_print(&state);

    puts("\nTesting dynamically allocated game_state...");
    if ((state_p = game_state_alloc(1)) == NULL) {
        perror("game_state_alloc returned NULL");
        return 1;
    }
    game_state_init(state_p);
    game_state_print(state_p);
    game_state_free(state_p);

    while (true) {
        int row = 0, col = 0;
        printf("Enter row and column for move: ");
        fflush(stdout);
        scanf("%d %d", &row, &col);
        if (game_state_valid_move(&state, row, col)) {
            game_state_make_move(&state, row, col);
            game_state_print(&state);
        }
    }

    return 0;
}
