#ifndef BITBOARD_H_INCLUDED
#define BITBOARD_H_INCLUDED

#include <stdint.h>

#define U64_ONE (UINT64_C(1))

uint64_t bb_set_square(uint64_t bb, uint8_t row, uint8_t col)
{
    return bb | (U64_ONE << 8 * row + col);
}

uint64_t bb_get_square(uint64_t bb, uint8_t row, uint8_t col)
{
    return bb & (U64_ONE << 8 * row + col);
}

uint64_t bb_flip_s(uint64_t gen, uint64_t pro);

uint64_t bb_flip_n(uint64_t gen, uint64_t pro);

uint64_t bb_flip_e(uint64_t gen, uint64_t pro);

uint64_t bb_flip_w(uint64_t gen, uint64_t pro);

uint64_t bb_flip_ne(uint64_t gen, uint64_t pro);

uint64_t bb_flip_se(uint64_t gen, uint64_t pro);

uint64_t bb_flip_sw(uint64_t gen, uint64_t pro);

uint64_t bb_flip_sw(uint64_t gen, uint64_t pro);

void bb_print(uint64_t black, uint64_t white);

#endif