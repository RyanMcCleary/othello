#include "game_state.h"

int main(int argc, char **argv) {
    struct game_state state;
    game_state_init(&state);
    game_state_print(&state);
    return 0;
}
