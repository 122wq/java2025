#include <stdio.h>
#include <string.h>
struct Thing
{
    double d;
    int i;
    struct Thing *next;
    char name[20];
};
void main()
{
    
    struct Thing steve;
    struct Thing myThings[10];
    for (int i = 0; i < 10; i++)
    {
        myThings[i] = (struct Thing){17.1, 0, &myThings[i + 1], "Things"};
    }
    myThings[9].next = &myThings[0];
    struct Thing *thingPointer;
    thingPointer = &myThings[0];
    thingPointer->d = 17.1;
    
    strcpy(thingPointer->name, "Number One");
    steve = (struct Thing){1.0, 42, &myThings[0], "A real boy"};
    for (int i = 0; i < 10; i++)
    {
        printf("%s\n",myThings[i].name);
    }
}