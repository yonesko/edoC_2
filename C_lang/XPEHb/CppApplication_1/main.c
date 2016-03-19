#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    
    struct ss {
        struct in {
            char * str;
            char st[600];
        } a;
    } b;
    
    printf("%li", sizeof(b.a));
    
    return (EXIT_SUCCESS);
}

