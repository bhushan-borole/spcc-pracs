#include<stdio.h>
#include "lib_num_properties.h"

int main(void){
    int a = 5, b = 180;
    printf("factorial of %d = %d\n", a, fact(a));
    printf("sum of first %d natural numbers is: %d\n", a, sum_n(a));
    printf("Factors of %d are: ", b);
    print_factors(b);
    return 0;
}
