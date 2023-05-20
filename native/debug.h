#ifndef DEBUG_H_INCLUDED
#define DEBUG_H_INCLUDED

#include <stdio.h>

#define DEBUG 1

#define debug_log(format, ... ) \
    do { if (DEBUG) fprintf(stderr, "%s:%d:%s(): " fmt, \
        __FILE__, __LINE__, __FUNC__, __VA_ARGS__); } while(0)
            
#endif