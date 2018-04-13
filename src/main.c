#include <stdio.h>

#define NEW_ORDER 1
#define GO_ADMIN 2
#define QUIT 3

int main()
{
    printf("Welcome to the Ordering Helper! Press enter to continue.");
    getchar();

    int option;

    while (1)
    {
        printf("____________________\n____________________\n\n");

        printf("1 - New order\n");
        printf("2 - Go to administrator mode\n");
        printf("3 - Quit\n\n");

        printf("Your input: ");
        scanf("%d", &option);

        if (option == NEW_ORDER)
        {
            // Go to creating new order
        }
        else if (option == GO_ADMIN)
        {
            // Go to switching to the administrator mode
        }
        else if (option == QUIT)
        {
            // Quit the program
            return 0;
        }
        else
        {
            printf("This input is invalid.\n");
        }
    }

    return 0;
}