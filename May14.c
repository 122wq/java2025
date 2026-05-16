#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define MAX_WORD_LENGTH 30

int main(int argc, char *argv[])
{
    char wordArray[MAX_WORD_LENGTH];  /* a string */
    char *word;  /* also a string, of length ? */

    printf("Enter a word (<%d chars): ", MAX_WORD_LENGTH);
    scanf("%s", wordArray);

    word = (char*)malloc(sizeof(char) * (strlen(wordArray) + 1));
    word = wordArray;
    
/* allocate enough space */
    if (word == NULL)
    {
        fprintf(stderr, "ERROR - malloc failed: Exiting Program!\n\n");
        exit(EXIT_FAILURE);
    }

    strcpy(word, wordArray);  /* copy the string into the allocated space */

    printf("  you entered: %s\n", word);

    free(word);  /* necessary? */

    return 0;
}

