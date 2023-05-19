#include <stdint.h>

#define U64_ONE (UINT64_C(1))

inline uint64_t bb_set_square(uint64_t bb, unsigned int square)
{
    return bb | (U64_ONE << square);
}

inline uint64_t bb_get_square(uint64_t bb, unsigned int square)
{
    return bb & (U64_ONE << square);
}

