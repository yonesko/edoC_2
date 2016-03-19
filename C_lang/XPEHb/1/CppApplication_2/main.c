#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLINES 100000
#define MAXLEN 500

char * lines[MAXLINES];

int readlines(char * lines[], int nlines);
int readline(char * line, int n);
void writelines(char * lines[], int nlines);
void myqsort(char *data[], int left, int right);

void intqsort(int data[], int left, int right);

int main(int argc, char** argv) {
    int nl = readlines(lines, MAXLINES);
    myqsort(lines, 0, nl-1);
    writelines(lines, nl);
  
    
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
int readlines(char * lines[], int nlines) {
    char line[MAXLEN];
    int l, nl=0;
    for(; nlines && (l=readline(line, sizeof(line)) > 0); nlines--, lines++, nl++) {
        *lines = (char *)malloc(l);
        strcpy(*lines, line);
    }
    return nl;
}
void writelines(char * lines[], int nlines) {
    while(nlines--)
        printf("%s", *lines++);
}
void myqsort(char *data[], int left, int right) {
    int i, last;
    if(left >= right) return;
    swap(data, left, (left+right)/2);
    last = left;
    for(i = left+1; i<=right; i++)
        if(strcmp(data[i], data[left]) < 0)
            swap(data, ++last, i);
    swap(data, left, last);
    myqsort(data, left, last-1);
    myqsort(data, last+1, right);
}
void iswap(int data[], int a, int b) {
    int t = data[a];
    data[a] = data[b];
    data[b] = t;
    
    int i;
    for(i=0; i < 7; i++)
        printf("%i ", data[i]);
    printf("\n");
}
void intqsort(int data[], int left, int right) {
    int i, last;
    if(left >= right) return;
    iswap(data, left, (left+right)/2);
    last = left;
    for(i = left+1; i<=right; i++)
        if(data[i] < data[left])
            iswap(data, ++last, i);
    iswap(data, left, last);
    intqsort(data, left, last-1);
    intqsort(data, last+1, right);
}
void swap(char *data[], int a, int b) {
    char *t = data[a];
    data[a] = data[b];
    data[b] = t;
}