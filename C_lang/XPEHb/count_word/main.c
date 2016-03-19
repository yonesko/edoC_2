#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define MAXWORD 100

struct node {
    char * word;
    struct node * left;
    struct node * right;
    int count;
    struct occurLines {
        int *arr;
        int len;
    } occs;
};


int getword(char * line, int n, int *linenum);
struct node *addtree(struct node *root, char *word, int line);
struct node *node();
void printtree(struct node *root);


int main(int argc, char** argv) {
    char w[MAXWORD];
    struct node *root = NULL;
    int l = 1;
    
    for(; getword(w, MAXWORD, &l) > 0; ) {
//        printf("num %i", l);
        root = addtree(root, w, l);
    }
    
    printtree(root);
    
    return (EXIT_SUCCESS);
}
struct node *node() {
    return (struct node *)malloc(sizeof(struct node));
}
int getword(char * line, int n, int *linenum) {
    char c;
    int f = 0, len = 0;
    
    for(;(c=getchar()) != EOF && n--; len++) {
        if (c == '\n')
            (*linenum)++;
        if(isalnum(c)) {
            f = 1;
            *line++ = c;
        } else if(f == 1)
            break;
        else
            f = 0;
    }
    *line = '\0';   
    return len;
}
struct node *addtree(struct node *root, char *word, int line) {
    int cond;
    if(root == NULL) {
        root = node();
        root->word = (char *)malloc(strlen(word) + 1);
        root->occs.arr = (int *)malloc(sizeof(int));
        
        root->occs.len = 1;
        root->occs.arr[root->occs.len - 1] = line;
        root->count = 1;
        strcpy(root->word, word);
    } else if((cond = strcmp(word, root->word)) == 0) {
        root->count++;
        root->occs.len += 1;
        root->occs.arr = (int *)realloc(root->occs.arr, root->occs.len * sizeof(int));
        root->occs.arr[root->occs.len - 1] = line;
    } else if (cond > 0) {
        root->right = addtree(root->right, word, line);
    } else if (cond < 0) {
        root->left = addtree(root->left, word, line);
    }
    return root;
}
void printtree(struct node *root) {
    if(root == NULL)
        return;
    int i;
    printtree(root->left);
    printf("%s %i occurs %i:", root->word, root->count, root->occs.len);
//    for(i=0; i<root->occs.len; i++)
//        printf("%i ", root->occs.arr[i]);
    printf("\n");
    printtree(root->right);
}
