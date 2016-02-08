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
};


int readline(char * line, int n);
struct node *addtree(struct node *root, char *word);
struct node *node();
void printtree(struct node *root);


int main(int argc, char** argv) {
    char w[MAXWORD];
    struct node *root = NULL;
    
    while(readline(w, MAXWORD) > 0)
        root = addtree(root, w);
    
    printtree(root);
    
    return (EXIT_SUCCESS);
}
struct node *node() {
    return (struct node *)malloc(sizeof(struct node));
}
int readline(char * line, int n) {
    char c;
    int f = 0, len = 0;
    
    for(;(c=getchar()) != EOF && n--; len++)
        if(isalnum(c)) {
            f = 1;
            *line++ = c;
        } else if(f == 1)
            break;
        else
            f = 0;
    *line = '\0';   
    return len;
}
struct node *addtree(struct node *root, char *word) {
    int cond;
    if(root == NULL) {
        root = node();
        root->count = 1;
        root->word = (char *)malloc(strlen(word) + 1);
        strcpy(root->word, word);
    } else if((cond = strcmp(word, root->word)) == 0) {
        root->count++;
    } else if (cond > 0) {
        root->right = addtree(root->right, word);
    } else if (cond < 0) {
        root->left = addtree(root->left, word);
    }
    return root;
}
void printtree(struct node *root) {
    if(root == NULL)
        return;
    printtree(root->left);
    printf("%s %i\n", root->word, root->count);
    printtree(root->right);
}
