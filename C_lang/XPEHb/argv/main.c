#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXLINE 100

int readline(char * line, int n);

int main(int argc, char **argv) {
    char l[MAXLINE];
    int number = 0, except = 0;
    int i, c, nl=0;
    
    argv++;
    while(argc && (*argv++)[0]=='-') 
        while(c = *argv[i]++)
            switch(c) {
                case 'n':
                    number = 1;
                    break;
                case 'v':
                    except = 1;
                    break;
            }
    
    
    
    
    
    while(readline(l, MAXLINE)>0) {
        if(strstr(l, *argv))
            if(number)
                printf("%d:%s", nl, l);
            else
                printf("%s", l);
        nl++;
    }
    
            
    return (EXIT_SUCCESS);
}

int readline(char * line, int n) {
    char c;
    int len = 0;
    for(; (c=getchar()) != EOF && c!='\n' && n; n--, len++)
        *line++ = c;
    *line++ = '\n';
    *line = '\0';
    return len;
}