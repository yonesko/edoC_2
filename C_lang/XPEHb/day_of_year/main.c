#include <stdio.h>
#include <stdlib.h>

int numday_of_year(int year, int mon, int day);
void mon_day(int year, int yearday, int *mon, int *day);

static int day_tab[][13] = {
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
};

int main(int argc, char** argv) {
    int Y = 2016, M = 5, D = 31;
    int m, d, n;
    n = numday_of_year(Y, M, D);
    mon_day(Y, n, &m, &d);
    
    printf("%i %i\n", m, d);
    printf("%i\n", n);
    
    return (EXIT_SUCCESS);
}

void mon_day(int year, int yearday, int *mon, int *day) {
    int leap = year%4 == 0 && year%100 != 0 || year%400 == 0;
    int i, sum=0;
    for(i=0; yearday > day_tab[leap][i]; i++)
        yearday -= day_tab[leap][i];
    *mon = i;
    *day = yearday;    
}
int numday_of_year(int year, int mon, int day) {
    int leap = year%4 == 0 && year%100 != 0 || year%400 == 0;
    int i;
    for(i=0; i<mon; i++)
        day += day_tab[leap][i];
    return day;
}

