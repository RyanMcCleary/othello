#include "bitboard.h"
#include <stdio.h>

uint64_t bb_flip_s(uint64_t gen, uint64_t pro)
{
    gen |= pro & (gen >>  8);
    pro &= (pro >>  8);
    gen |= pro & (gen >> 16);
    pro &= (pro >> 16);
    gen |= pro & (gen >> 32);
    return gen;
}

uint64_t bb_flip_n(uint64_t gen, uint64_t pro)
{
    gen |= pro & (gen <<  8);
    pro &= (pro <<  8);
    gen |= pro & (gen << 16);
    pro &= (pro << 16);
    gen |= pro & (gen << 32);
    return gen;
}

uint64_t bb_flip_e(uint64_t gen, uint64_t pro)
{
    pro &= UINT64_C(0xfefefefefefefefe);
    gen |= pro & (gen << 1);
    pro &= (pro << 1);
    gen |= pro & (gen << 2);
    pro &= (pro << 2);
    gen |= pro & (gen << 4);
    return gen;
}

uint64_t bb_flip_ne(uint64_t gen, uint64_t pro)
{
    pro &= UINT64_C(0xfefefefefefefefe);
    gen |= pro & (gen <<  9);
    pro &= (pro <<  9);
    gen |= pro & (gen << 18);
    pro &= (pro << 18);
    gen |= pro & (gen << 36);
    return gen;
}

uint64_t bb_flip_se(uint64_t gen, uint64_t pro) {
    pro &= UINT64_C(0xfefefefefefefefe);
    gen |= pro & (gen >>  7);
    pro &= (pro >>  7);
    gen |= pro & (gen >> 14);
    pro &= (pro >> 14);
    gen |= pro & (gen >> 28);
    return gen;
}

uint64_t bb_flip_w(uint64_t gen, uint64_t pro)
{
    pro &= UINT64_C(0x7f7f7f7f7f7f7f7f);
    gen |= pro & (gen >> 1);
    pro &= (pro >> 1);
    gen |= pro & (gen >> 2);
    pro &= (pro >> 2);
    gen |= pro & (gen >> 4);
    return gen;
}

uint64_t bb_flip_sw(uint64_t gen, uint64_t pro)
{
   pro &= UINT64_C(0x7f7f7f7f7f7f7f7f);
   gen |= pro & (gen >>  9);
   pro &= (pro >>  9);
   gen |= pro & (gen >> 18);
   pro &= (pro >> 18);
   gen |= pro & (gen >> 36);
   return gen;
}

uint64_t bb_flip_nw(uint64_t gen, uint64_t pro)
{
   pro &= UINT64_C(0x7f7f7f7f7f7f7f7f);
   gen |= pro & (gen <<  7);
   pro &=       (pro <<  7);
   gen |= pro & (gen << 14);
   pro &=       (pro << 14);
   gen |= pro & (gen << 28);
   return gen;
}

uint64_t bb_valid_moves(uint64_t curr, uint64_t opp)
{
    uint64_t result = 0;
    uint64_t temp;
    for (uint64_t square = 1; square != 0; square <<= 1) {
        if (curr & (bb_flip_n(curr, opp) << 9)) result |= square;
        else if (curr & (bb_flip_s(curr, opp) >> 8)) result |= square;
        else if (curr & (bb_flip_e(curr, opp) << 1)) result |= square;
        else if (curr & (bb_flip_w(curr, opp) >> 1)) result |= square;
        else if (curr & (bb_flip_w(curr, opp) >> 1)) result |= square;
        else if (curr & (bb_flip_ne(curr, opp) << 9)) result |= square;
        else if (curr & (bb_flip_se(curr, opp) >> 7)) result |= square;
        else if (curr & (bb_flip_sw(curr, opp) >> 9)) result |= square;
        else if (curr & (bb_flip_nw(curr, opp) << 7)) result |= square;
    }
    return result;
}

void bb_print(uint64_t black, uint64_t white)
{
    for (uint8_t row = 0; row < 8; row++) {
        for (uint8_t col = 0; col < 8; col++) {
            putchar(bb_get_square(black, row, col) != 0 ? 'B' : 
                (bb_get_square(white, row, col) != 0 ? 'W' : '*'));
        }
        putchar('\n');
    }
    putchar('\n');
}

int main(int argc, char **argv) {
    uint64_t bb_black = (U64_ONE << 3 * 8 + 3) | (U64_ONE << 4 * 8 + 4);
    uint64_t bb_white = (U64_ONE << 3 * 8 + 4) | (U64_ONE << 4 * 8 + 3);
    bb_print(bb_black, bb_white);
    bb_black = bb_set_square(bb_black, 2, 4);
    bb_print(bb_black, bb_white);
    bb_black = bb_flip_s(bb_black, bb_white);
    bb_print(bb_black, bb_white);
}