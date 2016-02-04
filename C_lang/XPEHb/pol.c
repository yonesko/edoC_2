#include <stdio.h>


int arr[1000];


main() {

int *x = arr,
*y = &arr[45]
;



printf("%li\n", y - x);

}


